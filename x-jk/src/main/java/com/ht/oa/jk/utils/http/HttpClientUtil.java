package com.ht.oa.jk.utils.http;

import com.ht.oa.jk.utils.constant.AppConst;
import com.ht.oa.jk.utils.log.LogUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private HttpClientUtil() {
    }

    private static final int CONNECTION_REQUEST_TIMEOUT = 60 * 1000; //从连接池获取连接的超时时间
    private static final int CONNECTION_TIMEOUT = 60 * 1000;  //握手的超时时间
    private static final int SOCKET_TIMEOUT = 60 * 1000; //数据包最大的间隔时间
    private static final int SO_TIMEOUT = 60 * 1000; //等待数据超时时间

    private static PoolingHttpClientConnectionManager poolConnManager;
    private static CloseableHttpClient closeableHttpClient;

    /**
     * 初始化块
     */
    static {
        ConnectionSocketFactory plainConnectionSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslConnectionSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainConnectionSocketFactory)
                .register("https", sslConnectionSocketFactory)
                .build();
        poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // Increase max total connection to 200
        poolConnManager.setMaxTotal(100);
        // Increase default max connection per route to 20
        poolConnManager.setDefaultMaxPerRoute(20);
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(SO_TIMEOUT).build();
        poolConnManager.setDefaultSocketConfig(socketConfig);

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECTION_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        closeableHttpClient = HttpClients.custom()
                .setConnectionManager(poolConnManager).setDefaultRequestConfig(requestConfig).build();
        if (poolConnManager.getTotalStats() != null) {
            System.out.println("new client pool " + poolConnManager.getTotalStats().toString());
        }
    }

    private static CloseableHttpClient getHttpClient() {
        return closeableHttpClient;
    }

    public static String sendGetRequest(String url, Map<String, String> rawParams) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        if (rawParams != null) {
            for (Map.Entry<String, String> tmp : rawParams.entrySet()) {
                params.add(new BasicNameValuePair(tmp.getKey(), tmp.getValue()));
            }
        }
        String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(params, AppConst.CHARSET));
        HttpGet httpGet = new HttpGet(url + "?" + paramsStr);
        HttpResponse response = getHttpClient().execute(httpGet);
        return EntityUtils.toString(response.getEntity(), AppConst.CHARSET);
    }

    public static String sendGetRequest(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = getHttpClient().execute(httpGet);
        return EntityUtils.toString(response.getEntity(), AppConst.CHARSET);
    }

    public static String sendPostRequest(String url, Map<String, String> rawParams) {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        if (rawParams != null) {
            for (Map.Entry<String, String> tmp : rawParams.entrySet()) {
                params.add(new BasicNameValuePair(tmp.getKey(), tmp.getValue()));
            }
        }
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params, AppConst.CHARSET));
            HttpResponse response = getHttpClient().execute(httpPost);
            return EntityUtils.toString(response.getEntity(), AppConst.CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sendPostRequest(String url, String params) {
        LogUtils.error("请求url:" + url);
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(params, "UTF-8"));
            HttpResponse response = getHttpClient().execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
