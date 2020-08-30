package com.zbhuang.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customerRouterLocal(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routeLocatorBuild = routeLocatorBuilder.routes();
        routeLocatorBuild.route("payment_router_three",
                r -> r.path("/s56").
                            uri("https://www.bilibili.com/")).build();
        return routeLocatorBuild.build();
    }

}
