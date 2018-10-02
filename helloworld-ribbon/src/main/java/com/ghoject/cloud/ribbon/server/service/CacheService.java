package com.ghoject.cloud.ribbon.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CacheService {
    @Autowired
    private RestTemplate restTemplate;

    public String refreshCache() {
        //刷新redis
        return restTemplate.getForObject("http://HELLOWORLD-CLIENT/redis/refreshCache",String.class);
    }

    public String getVersion() {
        //获取缓存有效期
        return restTemplate.getForObject("http://HELLOWORLD-CLIENT/redis/getExpireValue",String.class);
    }
}
