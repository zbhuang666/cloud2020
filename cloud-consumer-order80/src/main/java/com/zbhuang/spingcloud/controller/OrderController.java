package com.zbhuang.spingcloud.controller;

import com.zbhuang.spingcloud.lb.LoadBalance;
import com.zbhuang.springcloud.entities.CommonResult;
import com.zbhuang.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    @Resource
    RestTemplate restTemplate;
    // String PAYMENT_URL = "http://localhost:8001";
    String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    DiscoveryClient discoveryClient;

    @Resource
    LoadBalance loadBalance;


    @GetMapping(value = "consumer/payment/create")
    public CommonResult ceate(Payment payment) {
       return restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/" + id, CommonResult.class);
    }

    @GetMapping(value = "consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPaymentEntityInfo(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode()+"\t"+entity.getHeaders());
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    @GetMapping(value = "consumer/payment/createEntity")
    public CommonResult ceateEntity(Payment payment) {
        return restTemplate.postForEntity(PAYMENT_URL+"/payment/create",payment,CommonResult.class).getBody();
    }

    @GetMapping(value = "consumer/payment/lb")
    public String orderLb() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(serviceInstances == null || serviceInstances.size()<0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalance.instance(serviceInstances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForEntity(uri+"/payment/lb",String.class).getBody();
    }

    @GetMapping(value = "consumer/payment/zipkin")
    public String zipKinCollectInfo() {
        return restTemplate.getForEntity(PAYMENT_URL+"/payment/zipkin",String.class).getBody();
    }
}
