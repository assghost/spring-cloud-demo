package com.ghoject.cloud.helloworld.controller;

import com.ghoject.cloud.helloworld.config.DataSourceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
@RefreshScope
public class HomeController {

    @Value("${server.port}")
    private String serverPort;

    //@Value("${config.datasource.name}")
    private String dataSourceName;

    @Autowired
    private DataSourceConfiguration dataSourceConfiguration;

    @RequestMapping("")
    public String getHome() {
        System.out.println("dataSource name : " + dataSourceName);
        dataSourceConfiguration.loadConfig();

        return String.format("<h2>Hello World from %s, dataSource : %s</h2>"
                ,serverPort,dataSourceName);
    }
}
