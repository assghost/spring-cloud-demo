package com.ghoject.cloud.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HelloWorldClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldClientApplication.class, args);
    }
}
