package com.ghoject.cloud.feign.client.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 断路器
 * 请求被拒绝和发生异常时会进自动调用fallback方法
 * 当失败达到一定阈值，会触发熔断（默认10秒之内发生20次失败），之后所有请求都不会发送到服务端（默认熔断时间5秒）
 * hystrix.command.default.circuitBreaker.requestVolumeThreshold 规定时间内允许失败的次数，默认20
 * hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds 短路时间，默认5秒
 */
@Component
public class HelloFeignClientFallBackFactory implements FallbackFactory<HelloFeignClient>{

    @Override
    public HelloFeignClient create(Throwable throwable) {
        return new HelloFeignClient() {
            @Override
            public String sayHello() {
                throwable.printStackTrace();
                return " Error occur when sayHello ";
            }
        };
    }
}
