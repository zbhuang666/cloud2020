package com.zbhuang.springcloud.service.impl;

import com.zbhuang.springcloud.entities.CommonResult;
import com.zbhuang.springcloud.entities.Payment;
import com.zbhuang.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> PaymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回，----PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
