package zl.elasticsearch;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.Date;

public class EsClient {
    private static final Logger logger = LoggerFactory.getLogger(EsClient.class);
    private static EsClient esClient;
    private static TransportClient transportClient;
    private static BulkProcessor bulkProcessor;

    public static EsClient getEsClient(){
        if(esClient == null){
            synchronized (EsClient.class){
                if(esClient == null || transportClient ==null){
                    Settings setting = Settings.builder()
                            .put("cluster.name","es1")
                            .put("client.transport.sniff", true).build();
                    transportClient = new PreBuiltTransportClient(setting);
                    try {
                        String address="192.168.55.34,192.168.55.35";
                        if(address != null && address.length() > 0){
                            String[] addrs = address.split(",");
                            for(String addr:addrs){
                                transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(addr),9300));
                            }
                        }
                        esClient = new EsClient();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return esClient;
    }
    public void closeClient(){
        if(transportClient != null){
            transportClient.close();
        }
    }
    public TransportClient getTransportClient(){
        return transportClient;
    }
    public BulkProcessor getBulkProcessor(){
        if(bulkProcessor == null){
            bulkProcessor = BulkProcessor.builder(transportClient, new BulkProcessor.Listener() {
                public void beforeBulk(long executionId, BulkRequest request) {

                }

                public void afterBulk(long executionId, BulkRequest request, BulkResponse bulkResponse) {
                    logger.debug(( new Date().toLocaleString()+"-提交" + bulkResponse.getItems().length + "个文档，用时"+ bulkResponse.getTookInMillis() + "ms" + (bulkResponse.hasFailures() ? " 有文档提交失败！" : "")));
                }

                public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                    logger.debug(" 有文档提交失败！after failure=" + failure);
                }
            }).setBulkActions(1000)
            .setBulkSize(new ByteSizeValue(2, ByteSizeUnit.MB))
            .setFlushInterval(TimeValue.timeValueSeconds(10l))
            .setConcurrentRequests(2)
            .build();
        }

        return bulkProcessor;
    }
}
