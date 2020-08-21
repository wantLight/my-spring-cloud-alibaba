package com.itmuch.guavaCache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * 常见的缓存队列可以采用lru算法，所谓的lru其实本质的核心就在于：
 * 最近最久未使用的数据，可能在将来也不会再次使用，因此当缓存空间满了之后就可以将其淘汰掉。
 *
 * lru算法其实存在这缓存污染的问题，例如说
 * 某次批量查询操作，将一些平时用不到的数据也塞入到了队列中，将一些真正的热点数据给挤出了队列，造成缓存污染现象。
 *
 * 衍生出来了lru-k算法：
 * 给每个访问的元素都标识一个访问次数k值，这类算法需要多维护一条队列（暂且称之为访问队列），
 * 当数据的访问次数超过了k次之后，才会从原先的访问队列中转移到真正的lru队列里面。
 */
@SpringBootTest
public class GuavaDemo {


    @Test
    public void testCache(){
        // 优化：定义LoadingCache做一个全局性的callable回调操作处理
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        // 当缓存没有命中的时候，可以通过这里面的策略去加载数据信息
                        System.out.println("缓存命中失败，需要查询redis");
                        return "value"+key;
                    }
                });

        // 查询数据的时候，如果数据不存在，那么就需要写如何从内存里加载，每次查询都需要做一个callable的处理
        Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
        cache.put("k1","v1");
        //如果对象数据不存在，则返回一个null值
        Object v1=cache.getIfPresent("k1");
        Object v2 = null;
        try {
            v2 = cache.get("k2", () -> {
                System.out.println("该数值不存在，需要到redis去查询");
                return "k2";
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(v1);
        System.out.println(v2);
    }
}
