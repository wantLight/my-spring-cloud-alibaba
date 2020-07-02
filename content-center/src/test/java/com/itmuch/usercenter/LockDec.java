package com.itmuch.usercenter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDec {

    static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    static Lock readLock = rwLock.readLock();
    static Lock writeLock = rwLock.writeLock();
    public volatile boolean update = false;
    public void processData(){
        readLock.lock();
        if (!update){
            //必须先释放读锁
            readLock.unlock();
            //锁降级从写锁获取到开始
            writeLock.lock();
            try {
                if (!update) {
                    //准备数据的流程
                    update = true;
                }
                readLock.lock();
            }finally {
                writeLock.unlock();
            }
            //锁降级完成，写锁降级为读锁
        }
        try {
            //使用数据的流程
        }finally {
            readLock.unlock();
        }
    }

}
