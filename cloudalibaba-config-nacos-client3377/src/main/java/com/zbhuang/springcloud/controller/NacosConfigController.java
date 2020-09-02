package com.zbhuang.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class NacosConfigController {

    @Value("${config.info}")
    String configInfo;

    @GetMapping(value = "/config/info")
    public String getServerPort(){
        return configInfo;
    }
}
