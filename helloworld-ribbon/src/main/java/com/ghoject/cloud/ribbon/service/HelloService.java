package com.ghoject.cloud.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    public String sayHello() {
        return restTemplate.getForObject("http://HELLOWORLD-CLIENT/hello",String.class);
    }
}
