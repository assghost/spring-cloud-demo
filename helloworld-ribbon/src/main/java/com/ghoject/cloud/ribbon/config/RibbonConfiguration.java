package com.ghoject.cloud.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 对name为HELLOWORLD-CLIENT的服务端做自定义的负载均衡策略。
 * RibbonConfiguration，在主启动类之外定义，避免被扫描到
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
