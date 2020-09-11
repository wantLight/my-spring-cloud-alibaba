package com.itmuch.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {

    private SimpleDateFormat sdf;
    private String dateString;

    public MyThread(SimpleDateFormat sdf,String dateString) {
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {
            Date dateRef = sdf.parse(dateString);
            String newDateString = sdf.format(dateRef).toString();
            if(!newDateString.equals(dateString)) {
                System.out.println("ThreadName = " + Thread.currentThread().getName()
                        + "报错了  日期字符串：" + dateString + "转换成日期为：" + newDateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     *    测试单例的SimpleDateFormat类在多线程环境下中处理日期，极易出现日期转换错误的情况
     */
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray = new String[] {
                "2000-01-01","2000-01-02","2000-01-03",
                "2000-01-04","2000-01-05","2000-01-06",
                "2000-01-07","2000-01-08","2000-01-09",
                "2000-01-10"
        };

        MyThread[] threadArray = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new MyThread(sdf, dateStringArray[i]);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
    }

}
