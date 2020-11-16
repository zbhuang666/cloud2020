package com.zbhuang.springcloud.controller;

import com.zbhuang.springcloud.entities.CommonResult;
import com.zbhuang.springcloud.entities.Payment;
import com.zbhuang.springcloud.entities.Products;
import com.zbhuang.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    String serverPort;

    @Resource
    DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入结果*******O(∩_∩)O哈哈~"+result);
        if (result>0) {
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        } else {
            return new CommonResult(401,"插入数据库失败,serverPort:"+serverPort, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果*******"+payment);
        if (payment != null) {
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        } else {
            return new CommonResult(402,"查询失败,serverPort:"+serverPort, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object getServersInfo(){
        List<String> instances = discoveryClient.getServices();
        instances.forEach(instance -> log.info("***********element:"+instance));
        List<ServiceInstance> ServiceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstances.forEach(serviceInstance -> log.info("服务信息:"+serviceInstance.getInstanceId() + "\t" +serviceInstance.getHost() +"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri()));
        return discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getCurrentPort(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String feignReadTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/zipkin")
    public String zipKinCollect(){
        return "zipkin 正在采集消息，嘻嘻哈哈O(∩_∩)O";
    }

    @GetMapping(value = "/payment/consumption")
    public String getConsumerGoods() {
        paymentService.reduceGoods();
        Products products = paymentService.getGoods();
        return "调用"+serverPort+"库存为:"+products.getProductNum();
    }
}
