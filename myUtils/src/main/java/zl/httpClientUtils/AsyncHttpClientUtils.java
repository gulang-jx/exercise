package zl.httpClientUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class AsyncHttpClientUtils {
	private static PoolingHttpClientConnectionManager connMgr;
	private static RequestConfig requestConfig;
	
	static int macTotal = 100;
	static int connectTimeout = 6000;
	static int socketTimeout = 6000;
	static int connectionsRequestTimeout = 6000;
	
	static{
		init();
	}
	/**httpclient 初始化
	 * author zhouliang
	 * date 2017年9月12日
	 */
	private static void init(){
		connMgr = new PoolingHttpClientConnectionManager();
		connMgr.setMaxTotal(macTotal);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		configBuilder.setConnectTimeout(connectTimeout);
		configBuilder.setSocketTimeout(socketTimeout);
		configBuilder.setConnectionRequestTimeout(connectionsRequestTimeout);
		configBuilder.setStaleConnectionCheckEnabled(true);
		requestConfig = configBuilder.build();
	}
	/**
	 * 异步https   post
	 * author zhouliang
	 * date 2017年9月12日
	 */
	public static String asyncHttpsPost(String url,String body,Map<String,String> head) throws Exception{
        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(new TrustSelfSignedStrategy(){}).build();
        // Allow TLSv1 protocol only
        SSLIOSessionStrategy sslSessionStrategy = new SSLIOSessionStrategy(sslcontext,new String[] { "TLSv1" },
                null,SSLIOSessionStrategy.getDefaultHostnameVerifier());
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setSSLStrategy(sslSessionStrategy).setDefaultRequestConfig(requestConfig).build();
        String result = "";
        try {
            httpclient.start();
            HttpPost httpPost = new HttpPost(url);
            
    		StringEntity se = new StringEntity(body,Charset.forName("UTF-8"));
    		httpPost.setConfig(requestConfig);
      		httpPost.setEntity(se);
    		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
    		httpPost.setHeader("Content-type", "application/json;charset=utf-8");
    		head.keySet().forEach(key->{
    			httpPost.setHeader(key,head.get(key));
    		});
            Future<HttpResponse> future = httpclient.execute(httpPost, null);
            HttpResponse response = future.get();
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } finally {
            httpclient.close();
        }
        return result;
    
	}
	/**
	 * asyc  https get
	 * author zhouliang
	 * date 2017年9月12日
	 */
	public static String asyncHttpsGet(String url,Map<String,String> head)throws Exception{
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(new TrustSelfSignedStrategy(){}).build();
        // Allow TLSv1 protocol only
        SSLIOSessionStrategy sslSessionStrategy = new SSLIOSessionStrategy(sslcontext,new String[] { "TLSv1" },
                null,SSLIOSessionStrategy.getDefaultHostnameVerifier());
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setSSLStrategy(sslSessionStrategy).setDefaultRequestConfig(requestConfig).build();
        String result = "";
        try {
            httpclient.start();
            HttpGet httpGet = new HttpGet(url);
            
            httpGet.setConfig(requestConfig);
            head.forEach((key,value)->{
            	httpGet.setHeader(key,value);
            });
            /*httpGet.setHeader("Content-type", "application/json");
            httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");*/
            
            Future<HttpResponse> future = httpclient.execute(httpGet, null);
            HttpResponse response = future.get();
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } finally {
            httpclient.close();
        }
		
		return result;
	}
	
	/**
	 * async http ppost
	 * author zhouliang
	 * date 2017年9月12日
	 */
	public static String asyncHttpPost(String url,String body,Map<String,String>head) throws Exception{
		String result = null;
		Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy>create()
                .register("http", NoopIOSessionStrategy.INSTANCE)
                .build();
		//配置io线程
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(Runtime.getRuntime().availableProcessors()).build();
		//设置连接池大小
        ConnectingIOReactor ioReactor;
		ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor, null, sessionStrategyRegistry, null);
        connManager.setMaxTotal(200);
        //创建自定义的httpclient对象
		CloseableHttpAsyncClient client = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(connManager).build();//.createDefault();
		//创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		
		//装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		//设置参数到请求对象中
		StringEntity se = new StringEntity(body,Charset.forName("UTF-8"));
//		se.setContentEncoding("utf-8");
//		se.setContentEncoding("utf-8");
//		se.setContentType("application/json");
		httpPost.setEntity(se);

		System.out.println("请求地址："+url);
		System.out.println("请求参数："+nvps.toString());
		
		//设置header信息
		//指定报文头【Content-type】、【User-Agent】
		head.forEach((key,value)->{
			httpPost.setHeader(key,value);
		});
		/*httpPost.setHeader("Content-type", "application/json;charset=utf-8");
		httpPost.setHeader("Accept","application/json");
		httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");*/
		
		try{
			// Start the client
			client.start();
			//执行请求操作，并拿到结果（异步）
			Future<HttpResponse> future = client.execute(httpPost,null);
			HttpResponse response = future.get();
	        result = EntityUtils.toString(response.getEntity(), "utf-8");
		}finally{
			client.close();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void close(CloseableHttpAsyncClient client) {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
