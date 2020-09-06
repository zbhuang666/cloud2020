package com.zbhuang.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.zbhuang.springcloud.dao")
public class StorageService2002 {
    public static void main(String[] args) {
        SpringApplication.run(StorageService2002.class,args);
    }
}
