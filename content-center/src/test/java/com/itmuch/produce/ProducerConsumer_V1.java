package com.itmuch.produce;

public class ProducerConsumer_V1 {

    public static void main(String[] args) {
        ShareDataV1 shareDataV1 = new ShareDataV1();
        new Thread(() -> {
            shareDataV1.produce();
        }, "AAA").start();

        new Thread(() -> {
            shareDataV1.consumue();
        }, "BBB").start();

        new Thread(() -> {
            shareDataV1.produce();
        }, "CCC").start();

        new Thread(() -> {
            shareDataV1.consumue();
        }, "DDD").start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shareDataV1.stop();
    }

}
