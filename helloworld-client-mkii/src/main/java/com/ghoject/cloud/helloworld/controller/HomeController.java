package com.ghoject.cloud.helloworld.controller;

import com.ghoject.cloud.helloworld.bean.DBStatus;
import com.ghoject.cloud.helloworld.bean.DataSourceManger;
import com.ghoject.cloud.helloworld.bean.ZipkinLogger;
import com.ghoject.cloud.helloworld.dao.RedisDao;
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

    @Value("${config.email}")
    public String email;

    @Autowired
    private DataSourceManger dataSourceManger;

    @Autowired
    private DBStatus dbStatus;

    @Autowired
    private RedisDao redisDao;

    private ZipkinLogger logger = new ZipkinLogger(this.getClass());

    @RequestMapping("")
    public String getHome() {

        System.out.println("isMDCOpen : " + dbStatus.getIsMDCOpen());
        System.out.println("email : " + email);
        System.out.println("dataSources : " + dataSourceManger.getDataSources().get("mdc").getName());
        //System.out.println(1 / 0);
        logger.debug("customize trace message ");



        DBStatus o = redisDao.getValue("second");
        System.out.println(o.getIsMSPOpen());

        return String.format("<h2>Hello World from %s, dataSource : %s</h2>"
                ,serverPort,dataSourceManger.getDataSources().get("mdc").getName());
    }
}
