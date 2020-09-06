package com.zbhuang.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zbhuang.springcloud.entities.CommonResult;
import com.zbhuang.springcloud.entities.Payment;

/**
 * 需要static方法
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(200,"按客户自定义限流测试OK", new Payment(2020L, "CustomerBlockHandlerCall1"));

    }

    public static CommonResult handlerException1(BlockException exception){
        return new CommonResult(200,"按客户自定义限流测试OK", new Payment(2020L, "CustomerBlockHandlerCall2"));
    }

}
