package com.itmuch.contentcenter.sentineltest;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestControllerBlockHandlerClass {
    /**
     * 处理限流或者降级
     *
     * 容错方案：
     * 1.超时 2，限流 3.断路器（打开 断开 半开）
     *
     * 4.仓壁模式（大家不在一个篮子里，每个Controller都有自己的线程池，所以sentinel没用）
     * sentinel可以分配每个COntroller的线程数
     *
     * @param a
     * @param e
     * @return
     */
    public static String block(String a, BlockException e) {
        log.warn("限流，或者降级了 block", e);
        return "限流，或者降级了 block";
    }
}
