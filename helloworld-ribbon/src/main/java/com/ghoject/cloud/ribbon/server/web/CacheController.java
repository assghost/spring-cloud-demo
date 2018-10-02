package com.ghoject.cloud.ribbon.server.web;

import com.ghoject.cloud.ribbon.server.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cache")
@RefreshScope
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping("refresh")
    public String refresh() {
        return cacheService.refreshCache();
    }

    @RequestMapping("version")
    public String version() {
        return cacheService.getVersion();
    }

}
