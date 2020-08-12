package com.itmuch.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ArrayBlockingQueue: 基于数组的阻塞队列实现，其内部维护一个定长的数组，用于存储队列元素。
 * 线程阻塞的实现是通过ReentrantLock来完成的，数据的插入与取出共用同一个锁
 *
 * LinkedBlockingQueue:
 * 基于单向链表的阻塞队列实现，在初始化LinkedBlockingQueue的时候可以指定对立的大小，也可以不指定
 * 用于阻塞生产者、消费者的锁是两个（锁分离），因此生产与消费是可以同时进行的。
 */
public class ShareDataV3 {
    private static final int MAX_CAPACITY = 10; //阻塞队列容量
    private static BlockingQueue<Integer> blockingQueue= new LinkedBlockingDeque<>(MAX_CAPACITY); //阻塞队列
    private  volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public void produce() throws InterruptedException {
        while (FLAG){
            boolean retvalue = blockingQueue.offer(atomicInteger.incrementAndGet(), 2, TimeUnit.SECONDS);
            if (retvalue==true){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+ atomicInteger.get()+"成功"+"资源队列大小= " + blockingQueue.size());
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+ atomicInteger.get()+"失败"+"资源队列大小= " + blockingQueue.size());

            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"FLAG变为flase，生产停止");
    }
    public void consume() throws InterruptedException {
        Integer result = null;
        while (true){
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null==result){
                System.out.println("超过两秒没有取道数据，消费者即将退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费"+ result+"成功"+"\t\t"+"资源队列大小= " + blockingQueue.size());
            Thread.sleep(1500);
        }

    }
    public void stop() {
        this.FLAG = false;
    }
}
