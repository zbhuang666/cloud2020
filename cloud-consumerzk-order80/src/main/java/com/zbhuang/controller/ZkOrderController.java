package com.zbhuang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ZkOrderController {

    public static final String invoke_uri = "http://cloud-payment-service";

    @Resource
    RestTemplate restTemplate;

    @GetMapping(value = "consumer/payment/zk")
    public String zkInfo () {
        return restTemplate.getForObject(invoke_uri+"/payment/zk",String.class);
    }

}
