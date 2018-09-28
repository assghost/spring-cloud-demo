package com.ghoject.cloud.helloworld.bean;

import java.util.HashMap;

public class DataSourceManger {
    private HashMap<String,DataSource> dataSources;

    public HashMap<String, DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(HashMap<String, DataSource> dataSources) {
        this.dataSources = dataSources;
    }
}
