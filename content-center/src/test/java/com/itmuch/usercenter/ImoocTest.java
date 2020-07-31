package com.itmuch.usercenter;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Comparator.*;

@SpringBootTest
public class ImoocTest {


    @Test
    public void scheduleCourse() {
        //课程表 对应的时长t 截至日期d
        int[][] courses = {{2,5},{2,4},{1,6},{2,3}};
        // 以截至日期d排序
        Arrays.sort(courses, comparingInt(a -> a[1]));
        // [[2, 3], [2, 4], [2, 5], [1, 6]]
        System.out.println(Arrays.deepToString(courses));
        int count = 0, curtime = 0;
        for(int i = 0;i < courses.length;i++){
            //若可选，增加当前时间，并且将当前课程放入courses中
            if(curtime + courses[i][0] <= courses[i][1]){
                courses[count++] = courses[i];
                curtime += courses[i][0];
            }else{
                //否则，从courses中选一个耗时最长的课程，若这个耗时最长的课程比当前课程还长，则替换已选的那个课
                int max_i = i;
                for(int j = count - 1;j >= 0;j--){
                    if(courses[j][0] > courses[max_i][0])   max_i = j;
                }
                if(courses[max_i][0] > courses[i][0]){
                    curtime += courses[i][0] - courses[max_i][0];
                    courses[max_i] = courses[i];
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {

        //课程表
        int[][] course = {{2,5},{2,19},{1,8},{1,3}};
        
        StringBuffer stringBuffer = new StringBuffer();
        Vector vector = new Vector();
        List<Object> objectList = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

        List tesyt = new ArrayList<>(10);


    }



}
