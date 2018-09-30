package com.ghoject.cloud.feign.client.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfiguration {

    /**
     * 自定义httpclient连接池，默认配置在FeignHttpClientProperties
     * socketTimeout和connectTimeout以配置文件为准，在这里设置了不生效
     */
    @Bean
    public CloseableHttpClient httpClient() {
        System.out.println("===== 初始化 Apache httpclient 连接池 ====");
        // 生成默认请求配置
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        // 超时时间
        requestConfigBuilder.setSocketTimeout(30 * 1000);
        // 连接时间
        requestConfigBuilder.setConnectTimeout(5 * 1000);
        RequestConfig defaultRequestConfig = requestConfigBuilder.build();

        // 连接池配置
        // 长连接保持30秒
        final PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(5000);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(500);

        // httpclient 配置
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig);
        CloseableHttpClient client = httpClientBuilder.build();
        // 启动定时器，定时回收过期的连接
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("=====closeIdleConnections===");
                //关闭过期连接
                pollingConnectionManager.closeExpiredConnections();
                //关闭空闲连接
                pollingConnectionManager.closeIdleConnections(5, TimeUnit.SECONDS);
            }
        }, 10 * 1000, 10 * 60 * 1000);
        System.out.println("===== 初始化 Apache httpclient 连接池成功 ====");
        return client;
    }

//    @Bean
//    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
//        // httpClient连接配置，底层是配置RequestConfig
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
//        // 连接超时
//        clientHttpRequestFactory.setConnectTimeout(5 * 1000);
//        // 数据读取超时时间，即SocketTimeout
//        clientHttpRequestFactory.setReadTimeout(30 * 1000);
//        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
//        clientHttpRequestFactory.setConnectionRequestTimeout(200);
//        // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
//        // clientHttpRequestFactory.setBufferRequestBody(false);
//        return clientHttpRequestFactory;
//    }
//
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(){
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory());
//        return restTemplate;
//    }

}
