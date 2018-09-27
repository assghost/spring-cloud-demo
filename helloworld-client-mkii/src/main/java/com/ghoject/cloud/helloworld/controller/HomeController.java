package com.ghoject.cloud.helloworld.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HomeController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${datasource.name}")
    private String dataSource;

    @RequestMapping("")
    public String getHome() {

        return String.format("<h2>Hello World from %s, dataSource : %s</h2>"
                ,serverPort,dataSource);
    }
}
