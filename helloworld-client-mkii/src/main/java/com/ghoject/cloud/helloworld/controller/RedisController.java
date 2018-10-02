package com.ghoject.cloud.helloworld.controller;

import com.ghoject.cloud.helloworld.bean.DBStatus;
import com.ghoject.cloud.helloworld.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
@RefreshScope
public class RedisController {

    @Value("${config.redis.expire-key}:expire-key")
    private String expireKey;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private DBStatus dbStatus;

    @RequestMapping("refreshCache")
    public String refreshCache() {
        System.out.println("begin refresh cache...");
        redisDao.putValue("second",dbStatus);
        redisDao.putValue(expireKey, String.valueOf(System.currentTimeMillis()));
        System.out.println("refresh cache complete ");
        return "<h2>refresh complete</h2>";
    }

    @RequestMapping("getExpireValue")
    public String getExpireValue() {
        String version = redisDao.getValue(expireKey);
        System.out.println(version);
        return version;
    }
}
