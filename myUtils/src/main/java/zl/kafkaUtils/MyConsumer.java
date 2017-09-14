package zl.kafkaUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

/**
 * kafka消费者
 * @author zhouliang
 * @date 2017年9月14日
 */
public class MyConsumer {
    private final ConsumerConnector consumer;
    private MyConsumer() {
        Properties props = new Properties();
        //zookeeper 配置
        props.put("zookeeper.connect", "10.9.1.101:2181");

        //group 代表一个消费组
        props.put("group.id", "jd-group-test");

        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "14000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");
        //序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        ConsumerConfig config = new ConsumerConfig(props);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
    }

    void consume() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("operate_qoslog", new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap = 
                consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get("operate_qoslog").get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        int i = 0;
        long t = System.currentTimeMillis();
        while (it.hasNext()){
//        	 System.out.println(it.next().message());
        	it.next();
         	i++;
         	System.out.println(i);
        }
        System.out.println(System.currentTimeMillis()-t);
    }
    public static void main(String[] args) {
        new MyConsumer().consume();
    }
}
