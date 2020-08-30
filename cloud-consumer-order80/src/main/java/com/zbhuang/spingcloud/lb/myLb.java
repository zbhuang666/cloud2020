package com.zbhuang.spingcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class myLb implements LoadBalance{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * CAS获取请求次数
     * @return
     */
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("****请求次数: " + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        // 轮询获取服务
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
