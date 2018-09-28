package com.ghoject.cloud.ribbon.server.web;

import com.ghoject.cloud.ribbon.server.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hi")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("")
    @HystrixCommand(fallbackMethod = "simpleFallBack")
    public String sayHello() {
        return helloService.sayHello();
    }

    /**
     * 方法sayHello回退方法，可以指定将hystrix执行失败异常传入到方法中
     * @param p hystrix执行失败的传入方法的请求
     * @param e hystrix执行失败的异常对象
     * @return
     */
    public String simpleFallBack (long p, Throwable e) {
        return "Error Occur";
    }
}
