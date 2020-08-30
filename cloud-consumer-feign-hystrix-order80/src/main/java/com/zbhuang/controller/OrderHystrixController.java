package com.zbhuang.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zbhuang.service.PaymentFeignHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "paymentInfo_Global_Method")
public class OrderHystrixController {

    @Resource
    PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping("consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable Integer id) {
        return paymentFeignHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOut_Handler_Order",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000000")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        // int age = 10/0;
        String result = paymentFeignHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    public String paymentInfo_TimeOut_Handler_Order(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + " 系统繁忙80,id: " + id + "\t" + "o(╥﹏╥)o";
    }

    /**
     * 全局回调不可带有入参
     * @return
     */
    public String paymentInfo_Global_Method() {
        return "系统繁忙GLOBAL o(╥﹏╥)o";
    }

}
