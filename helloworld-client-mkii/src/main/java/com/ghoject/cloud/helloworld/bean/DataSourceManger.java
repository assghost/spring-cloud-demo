package com.ghoject.cloud.helloworld.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ConfigurationProperties(prefix = "config")
public class DataSourceManger {
    private HashMap<String,DataSource> dataSources;

    public HashMap<String, DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(HashMap<String, DataSource> dataSources) {
        this.dataSources = dataSources;
    }

    static class DataSource {
        private String name;

        private String pwd;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }
}
