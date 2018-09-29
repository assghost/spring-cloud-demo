package com.ghoject.cloud.feign.client.web;

import com.ghoject.cloud.feign.client.service.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hi")
public class HelloController {
    @Autowired
    private HelloFeignClient helloFeignClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("")
    public String sayHello() {
        List<ServiceInstance> instances = discoveryClient.getInstances("HELLOWORLD-CLIENT");
        instances.stream().forEach(inst -> System.out.print(inst.getUri() + "\n"));

        return helloFeignClient.sayHello();
    }

}
