package com.itmuch.future;

import java.util.concurrent.CompletableFuture;

/**
 * 开启两个线程，等到两个线程都计算结束的时候，我们进行最后的相加
 *
 * 还有**anyOff()**函数，可以承受多个CompletableFuture，会等待所有任务都完成。
 * 有个方法,是当第一个执行结束的时候，就结束，后面任务不再等了，可以看作充分条件。
 *
 */
public class CompletableCombineTest {

    public static void main(String[] args) throws Exception{
        CompletableFuture<Integer> oddNumber = CompletableFuture.supplyAsync(new OddNumberPlus());
        CompletableFuture<Integer> evenNumber = CompletableFuture.supplyAsync(new EvenCombine());
        long startTime = System.currentTimeMillis();
        // 用于等待两个子线程都结束了，再进行相加操作
        CompletableFuture<Integer> resultFuturn = oddNumber.thenCombine(evenNumber,(odd, even)-> odd + even);
        System.out.println(resultFuturn.get());
        System.out.println("0.开始了："+ (System.currentTimeMillis()-startTime) +"秒");
    }

}
