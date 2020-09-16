package com.itmuch.usercenter;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ExamRedisTests {
//    @Autowired
//    private JedisPool jedisPool;
//
//    private Jedis getJedis() {
//        return jedisPool.getResource();
//    }

    /**
     * 双向链表 List
     * -- 考生签到 ： 加入redis待叫号队列
     * -- 考评员从待叫号队列选择考生后： 待叫号队列考生移出，加入待考队列
     * -- 考试结束后： 考生从待考队列种移出
     *
     * List类型是按照插入顺序排序的字符串链表。和数据结构中的普通链表一样。我们可以在其头部(left)和尾部(right)添加新的元素。
     * 在插入时，如果该键并不存在，Redis将为该键创建一个新的链表。
     * 与此相反，如果链表中所有的元素均被移除，那么该键也将会被从数据库中删除
     * @throws Exception
     */
//    @Test
//    public void testList() throws Exception {
//
//        Jedis jedis = getJedis();
//
//        // 考生签到 ： 加入redis待叫号队列
//        // 如果指定的列表是空，则新建一个,然后插入。并返回链表中当前元素的数量。
//        Long lrangeResult2 = jedis.lpush("examKey", "张三201230164");
//        System.out.println("追加后链表中当前元素的数量"+lrangeResult2);
//        Long lrangeResultl = jedis.lpush("examKey", "李四201230165");
//        System.out.println("追加后链表中当前元素的数量"+lrangeResultl);
//
//        //取链表中的全部元素，其中0表示第一个元素，-1表示最后一个元素。
//        List<String> lrangeResult1 = jedis.lrange("examKey", 0, -1);
//        System.out.println("取链表中的全部元素:"+lrangeResult1);
//
//        // 考评员从待叫号队列选择考生后： 待叫号队列考生移出，加入待考队列
//        // 从头部(left)向尾部(right)变量链表，删除1个值等于a的元素，返回值为实际删除的数量。
//        Long lrangeResult6 = jedis.lrem("examKey", 1, "张三201230164");//2
//        System.out.println("实际删除的数量:"+lrangeResult6);
//
//
//        //取链表中的全部元素，其中0表示第一个元素，-1表示最后一个元素。
//        lrangeResult1 = jedis.lrange("examKey", 0, -1);
//        System.out.println("取链表中的全部元素:"+lrangeResult1);
//
//        // 考试结束后： 队列销毁
//        jedis.del("examKey");
//        jedis.close();
//    }

    //没有使用pipieline的情况下
//    public void testWithoutPipeline() {
//        Jedis jedis = new Jedis("127.0.0.1" , 6379);
//        for(int i = 1 ; i <= 10000 ; i++ ) {
//            jedis.hset("hashKey-" + i , "field-" + i , "value-" + i);
//        }
//    }
//
//    //使用pipeline的情况下
//    public void testPipeline() {
//        Jedis jedis = new Jedis("127.0.0.1" , 6379);
//        for(int i = 0 ; i < 100 ; i++ ) {
//            Pipeline pipeline = jedis.pipelined();
//            for(int j = i * 100 ; i < (i+1) * 100 ; j++ ) {
//                pipeline.hset("hashKey-" + j , "field-" + j , "value-" + j);
//            }
//            pipeline.syncAndReturnAll();
//        }
//    }

}
