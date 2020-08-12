package com.itmuch.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock，Condition的await和signal方法
 */
public class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            while (number != 0) {
                //等待，不能生产
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            while (number == 0) {
                //等待，不能消费
                condition.await();
            }

            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
