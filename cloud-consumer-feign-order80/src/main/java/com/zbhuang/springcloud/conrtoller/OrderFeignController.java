package com.zbhuang.springcloud.conrtoller;

import com.zbhuang.springcloud.entities.CommonResult;
import com.zbhuang.springcloud.entities.Payment;
import com.zbhuang.springcloud.service.OpenFeignServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    OpenFeignServices openFeignServices;

    @GetMapping(value = "consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return openFeignServices.getPaymentById(id);
    }

    @GetMapping(value = "consumer/payment/feign/timeout")
    public String feignReadTimeout(){
        return openFeignServices.feignReadTimeout();
    }

}
