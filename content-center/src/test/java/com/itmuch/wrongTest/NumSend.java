package com.itmuch.wrongTest;

import java.lang.reflect.Field;

/**
 * 形参和实参
 *
 * 参数值传递
 *
 *
 * 值传递：传递的是实际值，像基本数据类型
 * 引用传递：将对象的引用作为实参进行传递
 *
 * 自动装箱
 *
 */
public class NumSend {


    public static void main(String[] args) {
        // 自动装箱就是jdk调用了Integer的valueOf(int)的方法
        Integer a = 1;
        Integer b = 2;
        System.out.printf("a = %s, b = %s\n", a, b);
        // 这里传入的是实参 java基本类型数据作为参数是值传递，对象类型是引用传递
        swap(a, b);
        System.out.printf("a = %s, b = %s\n", a, b);
    }

    /**
     * 这里接受的是形参
     * 从作用域上看，形参只会在方法内部生效，方法结束后，形参也会被释放掉，所以形参是不会影响方法外的
     * @param a
     * @param b
     */
    public static void swap(Integer a, Integer b) {
        // TODO 实现
//        Integer temp = a;
//        a = b;
//        b = temp;
        int temp = a.intValue();
        try {
            // 要想交换
            Field value = Integer.class.getDeclaredField("value");
            value.setAccessible(true);
            value.set(a, b);
            value.set(b, new Integer(temp));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static void swap2(int a, int b) {
        // TODO 实现
        int temp = a;
        a = b;
        b = temp;
    }
}
