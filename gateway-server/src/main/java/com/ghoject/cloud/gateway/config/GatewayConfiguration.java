package com.ghoject.cloud.gateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfiguration {

    /**
     * 全局过滤器
     */
    @Bean
    @Order(-1)
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            System.out.println("first pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("last post filter");
            }));
        };
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(t -> t.path("/hi")
                        .filters(
                                f -> f.addResponseHeader("X-TestHeader","Gateway Test")
                        )
                        .uri("lb://RIBBON-SERVER"))
                .build();
    }

}
