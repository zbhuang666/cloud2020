package com.zbhuang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigNacos3377 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigNacos3377.class,args);
    }
}
