package com.zbhuang.service.impl;

import com.zbhuang.service.PaymentFeignHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignHystrixServiceImpl implements PaymentFeignHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "------------------paymentInfo_OK:80 callback";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "------------------paymentInfo_TimeOut:80 callback";
    }
}
