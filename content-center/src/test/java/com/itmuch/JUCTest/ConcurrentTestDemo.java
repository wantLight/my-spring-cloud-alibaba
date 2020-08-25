package com.itmuch.JUCTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ConcurrentTestDemo {

    public static void main(String[] args) {

        //并发数
        int currency = 20;

        //循环屏障
        CyclicBarrier cyclicBarrier = new CyclicBarrier(currency);

        for (int i = 0; i < currency; i++) {
            new Thread(() -> {
                //OrderServiceImplWithDisLock orderService = new OrderServiceImplWithDisLock();
                System.out.println(Thread.currentThread().getName() + "====start====");
                //等待一起出发
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                //orderService.createOrder();
            }).start();
        }
    }

}
