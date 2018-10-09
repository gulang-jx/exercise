package zl.redis;

import redis.clients.jedis.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RedisDataSource {
    private static RedisDataSource redisDataSource = new RedisDataSource();
    private ShardedJedisPool shardedJedisPool;
    private  JedisPool  jedisPool; //非切片连接池
    private int redisDbNumber;
    private int maxTotal;
    private int maxIdle;
    private int timeout;
    private String serverAddr;

    private RedisDataSource(){
        loadProperty();
        initjedispool();
    }
    public static RedisDataSource getInstance(){
        return redisDataSource;
    }

    public ShardedJedis getShardedJsdis(){
        return shardedJedisPool.getResource();
    }
    public void close(ShardedJedis shardedJedis){
        this.shardedJedisPool.returnResource(shardedJedis);
    }

    public Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        jedis.select(redisDbNumber);
        return jedis;
    }
    public void colse(Jedis jedis){
        jedisPool.returnResource(jedis);
    }
    /**
     * 初始化非切片连接池
     */
    public void initjedispool(){
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(timeout);
        config.setTestOnBorrow(true);
        config.setBlockWhenExhausted(true);
        String host=null;
        int port=-1;
        for (String server : serverAddr.split(",")) {
            String[] tmp = server.split(":");
            host = tmp[0];
            String strport = tmp[1];
            if(host!=null&&host!=""&&strport!=null&&strport!=""){
                port=Integer.valueOf(strport);
                break;
            }
        }
        //创建jedispool
        jedisPool=new JedisPool(config,host,port);
    }
    /**
     * 加载配置
     */
    private void loadProperty() {
        InputStream in = null;
        Properties prop;
        try {
            in = this.getClass().getClassLoader()
                    .getResourceAsStream("redis.properties");
            prop = new Properties();
            prop.load(in);
        } catch (IOException e) {
            throw new RuntimeException("在读取配置文件时发生错误！请确认配置的路径正确！");
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                throw new RuntimeException("在读取配置文件时发生错误！请确认文件没有被占用！");
            }
        }
        if (prop != null) {
            maxTotal = Integer.parseInt(prop.getProperty("redis.maxTotal"));
            maxIdle = Integer.parseInt(prop.getProperty("redis.maxIdle"));
            timeout = Integer.parseInt(prop.getProperty("redis.timeout"));
//			servers = (String)Constact.publicmap.get(Constact.REDIS_POOL);//prop.getProperty("redis.servers");
            redisDbNumber=Integer.parseInt(prop.getProperty("redisUse.RedisDbNumber",redisDbNumber+""));
            serverAddr = prop.getProperty("redis.servers");
            System.out.println("servers:"+serverAddr);
            System.out.println("redisDbNumber:"+redisDbNumber);
        }
    }
}
