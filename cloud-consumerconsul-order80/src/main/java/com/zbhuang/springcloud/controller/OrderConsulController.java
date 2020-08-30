package com.zbhuang.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderConsulController {

    public static final String invoke_uri = "http://consul-provider-payment";

    @Resource
    RestTemplate restTemplate;

    @GetMapping(value = "consumer/payment/consul")
    public String zkInfo () {
        return restTemplate.getForObject(invoke_uri+"/payment/consul",String.class);
    }

}
