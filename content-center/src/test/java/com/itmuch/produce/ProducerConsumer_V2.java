package com.itmuch.produce;

public class ProducerConsumer_V2 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    buffer.put();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    buffer.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

//        ShareData shareData = new ShareData();
//
//        new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                try {
//                    shareData.increment();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "AA").start();
//
//        new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                try {
//                    shareData.decrement();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "BB").start();
//
//        new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                try {
//                    shareData.increment();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "CC").start();
//
//        new Thread(()->{
//            for (int i = 0; i < 5; i++) {
//                try {
//                    shareData.decrement();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "DD").start();
    }
}
