package com.ghoject.cloud.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HelloWorldSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldSecurityApplication.class, args);
    }
}
