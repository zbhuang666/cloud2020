package com.zbhuang.springcloud.service.impl;

import com.zbhuang.springcloud.dao.OrderDao;
import com.zbhuang.springcloud.entities.Order;
import com.zbhuang.springcloud.service.AccountService;
import com.zbhuang.springcloud.service.OrderService;
import com.zbhuang.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    AccountService accountService;
    @Resource
    OrderDao orderDao;
    @Resource
    StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp_tx_group",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("=========开始新建订单");
        //1.新建订单
        orderDao.create(order);
        log.info("------------->订单微服务开始调用库存,做扣减Count");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------------->订单微服务开始调用库存,做扣减end");
        //3 扣减账户
        log.info("------------->订单微服务开始调用账户,做扣减Money");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------------->订单微服务开始调用账户,做扣减end");

        //4 修改订单状态
        log.info("------------->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("------------->修改订单状态结束");

        log.info("------------->下订单结束了");
    }
}
