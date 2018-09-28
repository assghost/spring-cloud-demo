package com.ghoject.cloud.helloworld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;


@Component
@RefreshScope
public class DataSourceConfiguration {

    @Autowired
    private Environment environment;

    public void loadConfig() {
        List<HashMap> list = Binder.get(environment).bind("config.datasource", Bindable.listOf(HashMap.class)).get();
        System.out.println(list);
    }
}
