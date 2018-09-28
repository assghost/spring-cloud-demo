package com.ghoject.cloud.helloworld.config;

import com.ghoject.cloud.helloworld.bean.DBStatus;
import com.ghoject.cloud.helloworld.bean.DataSourceManger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class HelloWorldConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "config")
    public DataSourceManger dataSourceManger() {
        return new DataSourceManger();
    }

    @Bean
    @ConfigurationProperties(prefix = "config.dbstatus")
    public DBStatus dbStatus() {
        return new DBStatus();
    }
}
