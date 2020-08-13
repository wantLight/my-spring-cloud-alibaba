package com.itmuch.produce;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Buffer {

    List<Integer> buffer = new LinkedList<Integer>();
    // 互斥量，控制buffer的互斥访问
    private Semaphore mutex = new Semaphore(1);

    // canProduceCount可以生产的数量（表示缓冲区可用的数量）。 通过生产者调用acquire，减少permit数目
    private Semaphore canProduceCount = new Semaphore(10);

    // canConsumerCount可以消费的数量。通过生产者调用release，增加permit数目
    private Semaphore canConsumerCount = new Semaphore(0);
    Random rn = new Random(10);

    public void get() throws InterruptedException {
        canConsumerCount.acquire();
        try {
            mutex.acquire();
            int val = buffer.remove(0);
            System.out
                    .println(Thread.currentThread().getName() + " 正在消费数据为：" + val + "    buffer目前大小为：" + buffer.size());
        } finally {
            mutex.release();
            canProduceCount.release();
        }

    }

    public void put() throws InterruptedException {
        // 就相当于将计数器减1
        canProduceCount.acquire();
        try {
            mutex.acquire();
            int val = rn.nextInt(10);
            buffer.add(val);
            System.out
                    .println(Thread.currentThread().getName() + " 正在生产数据为：" + val + "    buffer目前大小为：" + buffer.size());
        } finally {
            mutex.release();
            // 生产者调用release，增加可以消费的数量
            canConsumerCount.release();
        }
    }

}
