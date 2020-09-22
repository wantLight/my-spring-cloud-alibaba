package com.itmuch.usercenter;

import com.itmuch.usercenter.domain.entity.user.User;

import java.util.Optional;

/**
 * Optional 用以解决NullPointerException异常问题
 * 新的Optional类让我们可以以函数式编程的方式处理null值，
 * 抛弃了Java 8之前需要嵌套大量if-else代码块，使代码可读性有了很大的提高。
 *
 * 「尽量避免在程序中直接调用Optional对象的get()和isPresent()方法；」
 * 「避免使用Optional类型声明实体类的属性；」
 *
 */
public class OptionalTest {


    public static void main(String[] args) {
        String str = "Hello World";

        // empty()方法创建的对象没有值，如果对emptyOpt变量调用isPresent()方法会返回false，调用get()方法抛出NullPointerException异常。
        Optional<String> emptyOpt = Optional.empty();
        // of()方法使用一个非空的值创建Optional对象
        Optional<String> notNullOpt = Optional.of(str);
        // ofNullable()方法接收一个可以为null的值 (尽可能使用ofNullable()方法)
        Optional<String> nullableOpt = Optional.ofNullable(str);

        /**
         * 提取Optional对象中的值
         */
        // 使用Optional中提供的map()方法可以以更简单的方式实现：
        User user = new User();
        Optional<User> userOpt = Optional.ofNullable(user);
        // 以函数式编程的方式处理null值
        Optional<String> wxId = userOpt.map(User::getWxId).map(String::toUpperCase);
        // orElse()：如果有值就返回，否则返回一个给定的值作为默认值；
        System.out.println(wxId.orElse("lalala"));

        // of()方法使用一个非空的值创建Optional对象
        Optional<String> strOpt = Optional.of(str);
        String orElseResult = strOpt.orElse("Hello Shanghai");
        String orElseGet = strOpt.orElseGet(() -> "Hello Shanghai");
        String orElseThrow = strOpt.orElseThrow(
                () -> new IllegalArgumentException("Argument 'str' cannot be null or blank."));

        // filter()方法可用于判断Optional对象是否满足给定条件，一般用于条件过滤：
        Optional<String> optional = Optional.of("lw900925@163.com");
        optional = optional.filter(str2 -> str2.contains("163"));


    }
}
