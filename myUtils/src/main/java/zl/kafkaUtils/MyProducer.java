package zl.kafkaUtils;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.javaapi.producer.Producer;

/**
 * kafka生产者
 * @author zhouliang
 * @date 2017年9月14日
 */
public class MyProducer {
	private static final String TOPIC = "sync-errorlog";
    private static final String CONTENT = "This is a single message";
    private static final String BROKER_LIST = "10.9.1.101:9092";
    private static final String SERIALIZER_CLASS = "kafka.serializer.StringEncoder";
    
    public static void main(String[] args) {
    	Properties props = new Properties();
        props.put("serializer.class", SERIALIZER_CLASS);
        props.put("metadata.broker.list", BROKER_LIST);
        
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
    	JSONObject obj = new JSONObject();
    	obj.put("reqUrl", "www.baidu.com");
    	obj.put("reqBody", "{sdfasdfasdf}");
    	
    	
    	for(int i=0;i<1;i++){
    		obj.put("configId", new Random().nextInt(20)+10);
    		obj.put("syncId", i);
        	obj.put("syncTime", System.currentTimeMillis());
        	obj.put("businessId", i%10);
        	obj.put("syncresult", UUID.randomUUID());
        	
        	
    		KeyedMessage<String, String> message = new KeyedMessage<String, String>(TOPIC, obj.toString());
    		producer.send(message);
//    		System.out.println(i);
    	}
        
    }
}
