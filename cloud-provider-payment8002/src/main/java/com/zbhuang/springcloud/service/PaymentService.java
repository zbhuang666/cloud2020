package com.zbhuang.springcloud.service;

import com.zbhuang.springcloud.entities.Payment;
import com.zbhuang.springcloud.entities.Products;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
    public int reduceGoods();
    public Products getGoods();
}
