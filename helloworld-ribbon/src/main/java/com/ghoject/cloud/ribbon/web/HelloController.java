package com.ghoject.cloud.ribbon.web;

import com.ghoject.cloud.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hi")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("")
    public String sayHello() {
        return helloService.sayHello();
    }
}
