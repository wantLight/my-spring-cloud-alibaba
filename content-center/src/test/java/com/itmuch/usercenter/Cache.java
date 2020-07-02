package com.itmuch.usercenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {

    static Map<String, Object> map = new HashMap<String, Object>();
    static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    static Lock r = rwLock.readLock();
    static Lock w = rwLock.writeLock();
    //获取一个key对应的value
    public static final Object get(String key){
        r.lock();
        try {
            return map.get(key);
        }finally {
            r.unlock();
        }
    }
    //设置key对应的value值
    public static final Object put(String key, Object value){
        w.lock();
        try {
            return map.put(key, value);
        }finally {
            w.unlock();
        }
    }
    //清空所有内容
    public static final void clear(){
        w.lock();
        try {
            map.clear();
        }finally {
            w.unlock();
        }
    }
}
