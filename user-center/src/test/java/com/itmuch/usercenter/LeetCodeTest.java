package com.itmuch.usercenter;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2020-5-27 19:41
 */
@SpringBootTest
public class LeetCodeTest {
    /**
     * 二叉树遍历中序 -》 左中右
     */
    private boolean res = true;
    private long maxVal = Long.MIN_VALUE;

    public void test111(){


    }


    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        symmetric(root.left,root.right);
        return res;
    }

    private void symmetric(TreeNode rootLeft,TreeNode rootRight) {
        if (rootLeft == null){
            res = rootRight == null;
        }
        if (rootRight == null){
            res = rootLeft == null;
        }
        if (rootLeft.val != rootRight.val){
            res = false;
            return;
        }
        symmetric(rootLeft.left,rootLeft.right);
        symmetric(rootRight.left,rootRight.right);
    }


    @Test
    public void testLength() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.left = node7;
        node3.right = node6;
        System.out.println(treeDepth(root));
    }


    private int treeDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return 1+Math.max(left, right);
    }




    public boolean isValidBST(TreeNode root) {
        if (root == null){
            return true;
        }
        doCyc(root);
        return res;
    }

    private void doCyc(TreeNode root){
        if (root == null || !res){
            return;
        }
        doCyc(root.left);
        // 左 中 右 中序遍历为有序数组
        if (root.val > maxVal){
            maxVal = root.val;
        } else {
            res = false;
            return;
        }

        doCyc(root.right);


    }


    @Test
    public void lockSupport() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        List<String> list = new ArrayList<>();
        // 实现线程B
        final Thread threadB = new Thread(() -> {
            if (list.size() != 5) {
                LockSupport.park();
            }
            System.out.println("线程B收到通知，开始执行自己的业务...");
        });
        // 实现线程A
        Thread threadA = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                list.add("abc");
                System.out.println("线程A向list中添加一个元素，此时list中的元素个数为：" + list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                if (list.size() == 5)
                    LockSupport.unpark(threadB);
            }
        });
        threadA.start();
        threadB.start();

        System.out.println("-------------------------------");
        countDownLatch.await();
        System.out.println("end-------------------------------");
    }

    @Test
    public void rlock() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        List<String> list = new ArrayList<>();
        // 实现线程A
        Thread threadA = new Thread(() -> {
            lock.lock();
            for (int i = 1; i <= 10; i++) {
                list.add("abc");
                System.out.println("线程A向list中添加一个元素，此时list中的元素个数为：" + list.size());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                if (list.size() == 5)
                    condition.signal();

            }
            lock.unlock();
        });
        // 实现线程B
        Thread threadB = new Thread(() -> {
            lock.lock();
            if (list.size() != 5) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程B收到通知，开始执行自己的业务...");
            lock.unlock();
        });
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();

        System.out.println("-------------------------------");
        countDownLatch.await();
        System.out.println("end-------------------------------");
    }

    @Test
    public void message() throws InterruptedException {
        System.out.println("0000000000000");
        CountDownLatch countDownLatch = new CountDownLatch(10);

        Object lock = new Object();
        List<String> list = new ArrayList<>();
        // 实现线程A
        Thread threadA = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    list.add("abc");
                    System.out.println("线程A向list中添加一个元素，此时list中的元素个数为：" + list.size());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                    if (list.size() == 5)
                        System.out.println("发送线程B通知");
                        lock.notify();// 唤醒B线程
                }
            }
        });
        // 实现线程B
        Thread threadB = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (list.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("线程B收到通知，开始执行自己的业务...");
                }
            }
        });
        //　需要先启动线程B
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再启动线程A
        threadA.start();
        System.out.println("-------------------------------");
        countDownLatch.await();
        System.out.println("end-------------------------------");
    }

    @Test
    public void getFile(){
        String hi = "lalala.xlsx";
        int begin = hi.indexOf(".");
        int last = hi.length();
        //获得文件后缀名
        System.out.println(hi.substring(0, begin));
        System.out.println(hi.substring(begin, last));
    }

    @Test
    public void removeDuplicates() {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        if (nums.length == 0){
            return;
        }
        int i=0;
        for (int j=1;j<nums.length;j++){
            if (nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        System.out.println(i+1);
    }

}
