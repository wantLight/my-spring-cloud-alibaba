package com.itmuch.future;

import java.util.concurrent.CompletableFuture;

public class ErrorDemo {
    public static void main(String[] args) throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(ErrorDemo::fetchPrice);
        // 如果执行成功:
        cf.thenAccept((s) -> {
            System.out.println("price: " + s);
        });
        // 如果执行异常: 当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 500) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }
}
