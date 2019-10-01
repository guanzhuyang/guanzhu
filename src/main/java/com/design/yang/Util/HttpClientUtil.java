package com.design.yang.Util;

/**
 * @program: yang
 * @description: http
 * @author: 阳
 * @create: 2019-04-10 15:24
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by yuming.zhu on 15/7/6.
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final CloseableHttpClient hc;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        cm.setDefaultMaxPerRoute(20);
        cm.setMaxTotal(200);
        hc = HttpClients.custom().setConnectionManager(cm).build();
    }

    public static <T> T postJSON(String url, JSONObject head, String o, Class clazz) throws IOException {

        //

        enableSSL();
        RequestConfig defaultRequestConfig= RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .setConnectTimeout(20000).setSocketTimeout(20000)
                .build();
        org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry= RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient= HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();


        HttpPost post=new HttpPost(url);

        for(Map.Entry<String, Object> param : head.entrySet()) {
            post.setHeader(param.getKey(), param.getValue().toString());
        }
        post.setEntity(new StringEntity(o, "utf-8"));

        CloseableHttpResponse resp = null;
        try {
            logger.info("url:"+url);
            logger.info("body:"+o);
//            Mylogger.println("send body data"+o);
            logger.info("head:"+JSON.toJSONString(head));
            resp = httpClient.execute(post);
            String buf = IOUtils.toString(resp.getEntity().getContent(), "utf-8");
            logger.info("buf:"+buf);
            return StringUtils.isNotBlank(buf) ? (T) JSON.parseObject(buf, clazz) : null;
        } finally {
            if(resp != null) {
                try {
                    resp.close();
                } catch (IOException e1) {
                    logger.error(e1.getMessage());
                }
            }
        }
    }

    public static <T> T getJSON(String url, JSONObject head, Class clazz) throws IOException {

        enableSSL();
        RequestConfig defaultRequestConfig= RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .setConnectTimeout(20000).setSocketTimeout(20000)
                .build();
        org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry= RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient= HttpClients.custom().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(defaultRequestConfig).build();

        HttpGet get = new HttpGet(url);
        for(Map.Entry<String, Object> param : head.entrySet()) {
            get.setHeader(param.getKey(), param.getValue().toString());
        }

        CloseableHttpResponse resp = null;
        try {
            logger.info("url:"+url);
            logger.info("head:"+JSON.toJSONString(head));
            resp = httpClient.execute(get);
            String buf = IOUtils.toString(resp.getEntity().getContent(), "utf-8");
            logger.info("buf:"+buf);
            return StringUtils.isNotBlank(buf) ? (T) JSON.parseObject(buf, clazz) : null;
        } finally {
            if(resp != null) {
                try {
                    resp.close();
                } catch (IOException e1) {
                    logger.error(e1.getMessage());
                }
            }
        }
    }

    private static TrustManager manager =new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    private static void enableSSL(){
        try {
            SSLContext context=SSLContext.getInstance("TLS");
            context.init(null,new TrustManager[]{manager},null);
            socketFactory =new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static SSLConnectionSocketFactory socketFactory;

}

