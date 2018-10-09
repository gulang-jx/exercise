package zl.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) { //收到消息会调用
        System.out.println("get msg:"+message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {//订阅了频道会调用
        super.onSubscribe(channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {//取消订阅 会调用
        super.onUnsubscribe(channel, subscribedChannels);
    }
    @Test
    public void test(){
        Jedis jedis2=RedisDataSource.getInstance().getJedis();
        Subscriber subsc = new Subscriber();
        jedis2.subscribe(subsc,"test");
    }
}
