package com.itmuch.Junit5Test;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Jupiter提供了强大的断言方法用以验证结果，
 * 在使用时需要借助java8的新特性lambda表达式，均是来自org.junit.jupiter.api.Assertions包的static方法。
 *
 *
 * @BeforeEach：在每个单元测试方法执行前都执行一遍
 * @BeforeAll：在每个单元测试方法执行前执行一遍（只执行一次）
 * @DisplayName("商品入库测试")：用于指定单元测试的名称
 * @Disabled：当前单元测试置为无效，即单元测试时跳过该测试
 * @RepeatedTest(n)：重复性测试，即执行n次
 * @ParameterizedTest：参数化测试，
 * @ValueSource(ints = {1, 2, 3})：参数化测试提供数据
 */
public class TestAssertion {


    /**
     * assertNull与assertNotNull用来判断条件是否为·null
     */
    @Test
    @DisplayName("测试断言NotNull")
    public void testNotNull() {
        assertNotNull(new Object());
    }

    /**
     * assertTimeout用来判断执行过程是否超时
     */
    @Test
    @DisplayName("测试断言超时")
    void testTimeOut() {
        String actualResult = assertTimeout(ofSeconds(2), () -> {
            Thread.sleep(1000);
            return "a result";
        });
        System.out.println(actualResult);
    }


    /**
     * assertThrows用来判断执行抛出的异常是否符合预期
     * ，并可以使用异常类型接收返回值进行其他操作
     */
    @Test
    @DisplayName("测试断言抛异常")
    void testThrows() {
        ArithmeticException arithExcep = assertThrows(ArithmeticException.class, () -> {
            int m = 5/0;
        });
        assertEquals("/ by zero", arithExcep.getMessage());
    }

    /**
     * assertAll是组合断言，当它内部所有断言正确执行完才算通过
     */
    @Test
    @DisplayName("测试组合断言")
    public void testAll() {
        assertAll("测试item商品下单",
                () -> {
                    //模拟用户余额扣减
                    assertTrue("余额不足", 1 < 2);
                },
                () -> {
                    //模拟item数据库扣减库存
                    assertTrue(3 < 4);
                },
                () -> {
                    //模拟交易流水落库
                    assertNotNull(new Object());
                }
        );
    }

    /**
     * JUnit Jupiter通过使用@RepeatedTest(n)指定需要重复的次数
     */
    @RepeatedTest(3)
    @DisplayName("重复测试")
    public void repeatedTest() {
        System.out.println("调用");
    }

    /**
     * 参数化测试可以按照多个参数分别运行多次单元测试这里有点类似于重复性测试，
     * 只不过每次运行传入的参数不用。需要使用到@ParameterizedTest，
     * 同时也需要@ValueSource提供一组数据，它支持八种基本类型以及String和自定义对象类型，使用极其方便。
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("参数化测试")
    void paramTest(int a) {
        assertTrue(a > 0 && a < 4);
    }
}
