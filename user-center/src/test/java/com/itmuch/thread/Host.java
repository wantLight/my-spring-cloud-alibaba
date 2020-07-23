package com.itmuch.thread;

public class Host {
    public Data request(final int count, final char c) {

        System.out.println("request(" + count + ", " + c + ") BEGIN");

        // (1) 建立FutureData的实体

        final FutureData future = new FutureData();

        // (2) 为了建立RealData的实体，启动新的线程

        new Thread(() -> {
            //在匿名内部类中使用count、future、c。
            RealData realdata = new RealData(count, c);
            future.setRealData(realdata);

        }).start();

        System.out.println("request(" + count + ", " + c + ") END");

        // (3) 取回FutureData实体，作为传回值

        return future;

    }




    public static void main(String[] args) {

        System.out.println("main BEGIN");

        Host host = new Host();

        Data data1 = host.request(10, 'A');

        Data data2 = host.request(20, 'B');

        Data data3 = host.request(30, 'C');

        System.out.println("main otherJob BEGIN");

        try {

            Thread.sleep(200);

        } catch (InterruptedException e) {

        }

        System.out.println("main otherJob END");

        System.out.println("data1 = " + data1.getContent());

        System.out.println("data2 = " + data2.getContent());

        System.out.println("data3 = " + data3.getContent());

        System.out.println("main END");

    }
}
