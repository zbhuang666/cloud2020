package com.zbhuang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZkMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(ZkMain8004.class,args);
    }
}
