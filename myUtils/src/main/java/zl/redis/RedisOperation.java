package zl.redis;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class RedisOperation {

    /**
     * key 相关的操作
     */
    public void keyOperation(String key){
        Jedis jedis=RedisDataSource.getInstance().getJedis();
        jedis.exists(key);      //判断key值是否存在
        jedis.expire(key,1);        //设置key失效时间（单位：s） 返回： 1  操作成功   0 操作失败
        jedis.persist(key);     //  设置key永久失效  返回： 1  操作成功   0 操作失败
        Long pttl = jedis.pttl(key); //查询key的剩余有效时间  返回： integer（ms） -1永久有效 -2 key不存在
        jedis.ttl(key);                 //查询key的剩余有效时间  返回： integer（s） -1永久有效 -2 key不存在

        jedis.rename(key,"newKey");     //重新设置key的值，由原来的${key} 设置为“newKey”
        jedis.renamenx(key,"newKey");   // 重新设置key的值，由原来的${key} 设置为“newKey”,仅当newkey不存在时，才命名为newkey，1-设置成功，0-设置失败
        jedis.type(key);        //返回key对应存贮的值的类型
        jedis.move(key,10); //将当前db中的可以移到db10
        jedis.close();
    }

    /**
     * 字符串类型的值操作
     */
    public void StringOperate(String key,String value){
        Jedis jedis=RedisDataSource.getInstance().getJedis();
        jedis.set(key,value);   //设置key的值为value
        jedis.setnx(key, value);//设置key的值为value(只有key不存在才成功，避免被覆盖)，返回integer，1-设置成功，0-设置失败
        jedis.setex(key,10,value);  //key赋值为value,并设置过期时间（s）
        jedis.mset(key,value,key,value);//同时设置一个或多个key的值
        jedis.append(key, value);//key追加值，返回value长度，从1开始
        jedis.incr(key);        //将key中存储的数字值加一，并返回结果值
        jedis.incrBy(key,10);//将key中存储的数字值加上指定值（increment），并返回结果值
        jedis.decr(key);            //将key中存储的数字减一,并返回结果值
        jedis.decrBy(key,10);//将key中存储的数字值减去指定值（decrement），并返回结果值


        jedis.get(key);         //返回key值
        List<String> mget = jedis.mget(key, "key1", "key2");//获取给定（一个或多个）key的值

        jedis.getrange(key,0,10);   //key中value的子字符串，从0开始
        jedis.strlen(key);  //key的value长度，从1开始
        jedis.close();
    }

    /**
     * hash类型操作
     * @param key
     */
    public void hashOperation(String key){
        Jedis jedis=RedisDataSource.getInstance().getJedis();

        jedis.hset(key,"field","value");  //将key的hash表中的字段field设值为value（由field和关联的value组成的map），字段不存在则生成，存在则覆盖（相当于更新）
        jedis.hmset(key,new HashMap<String, String>()); //同时为key的hash表设置多个字段与值
        jedis.hsetnx(key,"field","value");  // 只有字段field不存在是，将key的hash表中添加字段field并设值为value
        jedis.hgetAll(key);//获取key中所有的字段和值
        jedis.hexists(key,"field");//key中是否存在指定的字段返回integer，1-存在，0-不存在

        jedis.hget(key,"field");//获取key中指定字段的值
        List<String> hmget = jedis.hmget(key, "field1", "field2");//获取所有给定字段的值

        jedis.hkeys(key);//获取key中的所有字段
        jedis.hlen(key);//key的字段数量,返回integer
        jedis.hvals(key);//获取hash表中所有值
        jedis.hdel(key,"field1","field2");//删除一个或多个hash字段

        jedis.close();
    }

    /**
     * list类型操作
     */
    public void listOperation(String key){
        Jedis jedis=RedisDataSource.getInstance().getJedis();

        jedis.lpush(key,"value1","value2");//将一个或多个值插入到列表头部，返回插入数量，从1开始
        jedis.lpushx(key,"value1","value2");//将一个值插入到已存在的列表头部，返回integer，0-插入失败，成功返回当前list数量
        jedis.rpush(key,"value1","value2");//在列表中添加一个或多个值，返回integer，0-插入失败，成功返回当前list数量
        jedis.rpushx(key,"value1","value2");//为已存在的列表添加值，返回integer，0-插入失败，成功返回当前list数量
        jedis.lset(key,1,"value");//通过索引设置列表元素的值

        jedis.linsert(key, BinaryClient.LIST_POSITION.AFTER,"pivot","value");//在列表的元素(pivot)前或者后插入元素(value),返回integer，-1-插入失败，成功返回当前list数量
        jedis.brpoplpush("source","destination",1);//从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹

        jedis.lindex(key,1);//通过索引获取列表中的元素
        jedis.llen(key);//获取列表长度
        jedis.lrange(key,0,10);//获取列表指定范围内的元素，start、stop超出范围，只返回范围内存在的元素
        jedis.lpop(key);   //移出并获取列表的第一个元素
        jedis.rpoplpush(key,"dstkey");//移除列表的最后一个元素，并将该元素添加到另一个列表并返回
        jedis.lrem(key,10,"value");//移除列表中count个value元素，返回移除成功的数量
        jedis.rpop(key);//移除并获取列表最后一个元素
        jedis.ltrim(key,0,10);//对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。

        jedis.close();
    }

    /**
     * set（集合）类型操作
     */
    public void setOpertion(String key){
        Jedis jedis=RedisDataSource.getInstance().getJedis();

        jedis.sadd(key,"member1","member2");//向集合添加一个或多个元素，返回成功添加个数

        jedis.scard(key);   //获取集合的元素个数
        Set<String> smembers = jedis.smembers(key);//返回集合所有元素
        jedis.srandmember(key,10);//返回集合中n个随机数
        jedis.sismember(key,"member");//判断member元素是否在集合key中，返回integer，1-存在，0-不存在
        jedis.sdiff(key,"keys");    //返回给定所有集合差集,如果只给一个key将列出所有集合元素
        jedis.sdiffstore("dstkey",key,"key1");//返回给定所有集合差集，并将差集存储在dstkey中
        jedis.sinter(key,"key1");//返回给定所有集合交集
        jedis.sinterstore("dstkey",key,"key1");//返回给定所有集合交集,并将差集存储在dstkey中
        jedis.sunion(key,"key1");//返回所有给定集合的并集
        jedis.sunionstore("dstkey",key,"key1");//返回给定所有集合并集,并将差集存储在dstkey中

        jedis.srem(key,"member1","member2");//移除集合中一个或多个元素，返回移除成功个数
        jedis.spop(key);//返回并移除集合中的一个随机元素


        jedis.close();
    }

    public void zsetOperation(String key){
        Jedis jedis=RedisDataSource.getInstance().getJedis();

        jedis.zadd(key,new HashMap<String, Double>());//添加一个或多个元素，并给每个元素设置分数,返回成功插入个数
        jedis.zcard(key);   //获取集合的元素个数
        jedis.zcount(key,"min","max");//统计score在[min,max]区间的元素数量

        jedis.zlexcount(key,"min","max");//在有序集合中计算指定字典区间内成员数量,字典序(lexicographical order )
        jedis.zrange(key,0,10);//返回按score从小到大排序后且索引在[start,stop]区间的元素，从0开始
        jedis.zrangeByScore(key,"min","max");//返回按score从小到大排序后且分数在[min,max]区间的元素
        jedis.zrevrange(key,0,10);//返回按score从大到小排序后且索引在[start,stop]区间的元素，从0开始
        jedis.zrevrangeByScore(key,0,10);//返回按score从大到小排序后且分数在[min,max]区间的元素

        jedis.zrangeByLex(key,"min","max");//通过字典区间返回有序集合的成员
        jedis.zrank(key,"member");//返回元素member的索引，不存在nil
        jedis.zrevrank(key,"member");//返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
        jedis.zscore(key,"member");//返回有序集合中，元素member分数
        jedis.zscan(key,"cursor");//迭代有序集合中的元素（包括元素的分值）
        jedis.zunionstore("dstkey",key,"key1","key2");//计算给定的一个或多个有序集的并集，并存储在新的 key 中
        jedis.zinterstore("dstkey",key,"key1","key2");//计算给定的一个或多个有序集的交集，并存储在新的 key 中
        jedis.zincrby(key,1,"memeber");//有序集合中对指定元素的分数加上增量 increment(1)
        jedis.zrem(key,"member1","member2");//移除有序集合中的一个或多个元素
        jedis.zremrangeByLex(key,"member1","member2");//移除有序集合中给定的字典区间的所有成员
        jedis.zremrangeByRank(key,1,10);//移除有序集合中给定的排名区间的所有成员
        jedis.zremrangeByScore(key,1,10);//移除有序集合中给定的分数区间的所有成员


        jedis.close();
    }
}
