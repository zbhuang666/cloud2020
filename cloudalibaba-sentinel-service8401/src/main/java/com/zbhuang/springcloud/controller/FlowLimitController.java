package com.zbhuang.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "A transaction si used";
    }

    @GetMapping("/testB")
    public String testB(){
        return "B transaction si used";
    }

    /**
     * 慢调用比例
     * @return
     */
    @GetMapping("/testC")
    public String testC(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "C transaction si used";
    }

    /**
     * 异常比例
     * @return
     */
    @GetMapping("/testD")
    public String testD(){
        int age = 10/0;
        return "D transaction si used";
    }


    /**
     * 异常数
     * @return
     */
    @GetMapping("/testE")
    public String testE(){
        int age = 10/0;
        return "E transaction si used";
    }

    /**
     * 热点key 需要重启服务
     * @return
     */
    @GetMapping("/hostKey")
    @SentinelResource(value = "hostKey", blockHandler = "hostKeyMethodBack")
    public String hostKey(@RequestParam(value = "p1",required = false) String p1,
                          @RequestParam(value = "p2",required = false) String p2){
        return "hostKey ---- O(∩_∩)O哈哈~";
    }

    public String hostKeyMethodBack(String p1, String p2, BlockException exception) {
        return "hostKey ----- o(╥﹏╥)o";
    }
}
