package com.itmuch.guavaCache;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;

/**
 * 布隆过滤器用于在海量数据中检索一个元素是否在一个集合中
 * 1. 不存在漏报（False Negative），即某个元素在某个集合中，肯定能报出来。但是可能存在误报（False Positive）
 * 2. 能够预先知悉大致不重复数据量规模和可接受错误率
 *
 */
@SpringBootTest
public class MyBloomFilter {

    /**
     * 一个空的布隆过滤器是一个由m个二进制位构成的数组。
     * 我们需要k个hash函数来计算输入的hash值。
     * 当我们向过滤器中添加一个元素事，k个hash函数计算出的索引值 h1(x), h2(x)，… hk(x)被设置位1。例如将geeks加入到过滤器中，我们用到3个hash函数，过滤器的长度为10，初始
     * 值被设置为0。
     */
    @Test
    public void test(){
        // 创建布隆过滤器时要传入期望处理的元素数量，及最期望的误报的概率
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                500,
                0.01);
        filter.put(1);
        filter.put(2);
        filter.put(3);

        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        System.out.println(filter.mightContain(100));

    }

}
