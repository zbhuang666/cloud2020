package com.zbhuang.springcloud.conrtoller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class NacosOrderController {

    @Value("${server-url.nacos-user-service}")
    String PATH_URI;

    @Resource
    RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PATH_URI+"/payment/nacos/"+id,String.class);
    }

}
