package com.itmuch.usercenter;

public class MulTest {


        public static void main(String[] args) {
            Thread thread = new Thread(new SubThread());
            thread.run();
            System.out.println("run ----");
            thread.start();
            System.out.println("start ----");
        }




}
