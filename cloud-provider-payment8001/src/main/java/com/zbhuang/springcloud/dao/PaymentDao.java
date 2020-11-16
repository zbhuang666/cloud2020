package com.zbhuang.springcloud.dao;

import com.zbhuang.springcloud.entities.Payment;
import com.zbhuang.springcloud.entities.Products;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
    public int reduceGoods();
    public Products getGoods();
}
