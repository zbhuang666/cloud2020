package com.zbhuang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulMain80.class,args);
    }
}
