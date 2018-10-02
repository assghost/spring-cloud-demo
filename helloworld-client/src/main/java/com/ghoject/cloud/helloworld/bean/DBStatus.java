package com.ghoject.cloud.helloworld.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config.dbstatus")
public class DBStatus {
    private String isMDCOpen;

    private String isMSPOpen;

    public String getIsMDCOpen() {
        return isMDCOpen;
    }

    public void setIsMDCOpen(String isMDCOpen) {
        this.isMDCOpen = isMDCOpen;
    }

    public String getIsMSPOpen() {
        return isMSPOpen;
    }

    public void setIsMSPOpen(String isMSPOpen) {
        this.isMSPOpen = isMSPOpen;
    }
}
