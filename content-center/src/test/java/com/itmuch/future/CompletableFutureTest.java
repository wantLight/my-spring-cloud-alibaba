package com.itmuch.future;

import java.util.concurrent.CompletableFuture;

/**
 * 本例中并没有显示的创建任务连接池，程序会默认选择一个任务连接池ForkJoinPool.commonPool()
 * ForkJoinPool始自JDK7，叫做分支/合并框架。可以通过将一个任务递归分成很多分子任务，形成不同的流，进行并行执行，同时还伴随着强大的工作窃取算法。极大的提高效率。
 * CompletableFuture，更加感觉到Java8的强大，强大的流概念、行为参数化、高效的并行理念
 *
 * 使用Future获得异步执行结果时，要么调用阻塞方法get()，要么轮询看isDone()是否为true，这两种方法都不是很好，因为主线程也会被迫等待。
 *
 * 从Java 8开始引入了CompletableFuture，它针对Future做了改进，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // 30
        final int evenNumber = 2 + 4 + 6 + 8 + 10;
        // 25
        CompletableFuture<Integer> oddNumber = CompletableFuture.supplyAsync(new OddNumberPlus());
        try {
            Thread.sleep(1000);
            System.out.println("0.开始了："+ (System.currentTimeMillis()-startTime) +"秒");
            // 实现回调，自动后续操作
            oddNumber.thenAccept(oddNumberResult->
            {
                System.out.println("1.开始了："+ (System.currentTimeMillis()-startTime) +"秒");
                System.out.println("此时计算结果为："+(evenNumber+oddNumberResult));
            });
            Integer integer = oddNumber.get();
            System.out.println(integer);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
