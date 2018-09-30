package zl.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.ClearScrollRequestBuilder;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ElasticSearchOpertion {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchOpertion.class);



    public static void  buketInsert(JSONObject json,String index,String type){
        BulkProcessor bulkProcessor = EsClient.getEsClient().getBulkProcessor();
        bulkProcessor.add(indexRequest(index,type,"",json.toJSONString()));
    }

    public List<String> queryData(String index,String type,long startTime,long endTime){
        List<String> list = null;
        TransportClient transportClient = EsClient.getEsClient().getTransportClient();
        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("offtime")
                .lt(startTime)
                .gte(endTime);
        SearchResponse searchResponse = transportClient
                .prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DEFAULT)
                .setQuery(queryBuilder)
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setScroll(TimeValue.timeValueMinutes(1))
                .setSize(1000)
                .execute().actionGet();
        String scrollId = searchResponse.getScrollId();
        SearchHits searchHits;
        long total;
        while ((searchHits=searchResponse.getHits()).getHits().length != 0){
            for(SearchHit hit:searchHits.getHits()){
                total = searchHits.getTotalHits();
                String msg = hit.getSourceAsString();
                list.add(msg);
            }
            searchResponse = transportClient.prepareSearchScroll(scrollId).setScroll(new TimeValue(60000)).execute().actionGet();
        }
        clearScroll(transportClient, scrollId);    //clear srollId

        return list;
    }

    public static IndexRequest indexRequest(String index,String type,String id,String jsonStr){
        IndexRequest indexRequest = null;
        try {
            if(id != null && id.length() > 0){
                indexRequest = new IndexRequest(index,type,id).source(jsonStr, XContentType.JSON);
            }else{
                indexRequest = new IndexRequest(index,type).source(jsonStr, XContentType.JSON);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return indexRequest;
    }
    public static boolean clearScroll(Client client, String scrollId){
        ClearScrollRequestBuilder clearScrollRequestBuilder = client.prepareClearScroll();
        clearScrollRequestBuilder.addScrollId(scrollId);
        ClearScrollResponse response = clearScrollRequestBuilder.get();
        return response.isSucceeded();
    }

}
