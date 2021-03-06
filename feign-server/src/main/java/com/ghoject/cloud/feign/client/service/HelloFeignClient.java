package com.ghoject.cloud.feign.client.service;

import com.ghoject.cloud.feign.client.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "HELLOWORLD-CLIENT"
        , configuration = FeignConfiguration.class
        , fallbackFactory = HelloFeignClientFallBackFactory.class)
public interface HelloFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String sayHello();
}