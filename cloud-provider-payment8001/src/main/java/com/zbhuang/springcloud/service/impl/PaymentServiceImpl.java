package com.zbhuang.springcloud.service.impl;

import com.zbhuang.springcloud.dao.PaymentDao;
import com.zbhuang.springcloud.entities.Payment;
import com.zbhuang.springcloud.entities.Products;
import com.zbhuang.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public int reduceGoods() {
        return paymentDao.reduceGoods();
    }

    @Override
    public Products getGoods() {
        return paymentDao.getGoods();
    }
}
