package com.itmuch.usercenter;

public class SubThread implements Runnable{

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行本方法的线程:"+Thread.currentThread().getName());
    }

}