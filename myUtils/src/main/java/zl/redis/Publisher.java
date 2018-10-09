package zl.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class Publisher {
    public static void publishMsg( Jedis jedis,String channel,String msg) {
        jedis.publish(channel,msg);
        jedis.close();
    }

    @Test
    public void test() {
        Jedis jedis=RedisDataSource.getInstance().getJedis();
        for (int i = 0; i < 10; i++) {
            jedis.publish("test","hello world");
        }

    }
}
