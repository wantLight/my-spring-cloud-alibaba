package com.itmuch.algorithm;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 解决一个回溯问题，实际上就是一个决策树的遍历过程
 *
 * 其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」
 *
 * result = []
 * def backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *
 *     for 选择 in 选择列表:
 *         排除不合法的选择
 *         做选择
 *         backtrack(路径, 选择列表)
 *         撤销选择
 *
 *
 */
public class Backtracking {

    private static List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    static List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素
    // 结束条件：nums 中的元素全都在 track 中出现
    static void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (track.contains(nums[i]))
                continue;
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }


    /**
     * N 皇后问题
     * @param args
     */
    static int resultCount = 0;

    // 皇后位置满足约束条件的判定函数
    private static boolean place(int[] arr, int s) {
        for(int i = 0; i < s; i++) {

            //判定s行X[s]位置上的皇后，与1至s-1行上各皇后的位置是否满足约束条件
            // X[i] = X[s]，则第i行与第s行皇后在同一列上
            // 如果第i行的皇后在第j列，第s行皇后在第t列，即X[i] = j和X[s] = t，则只要i-j = s-t或者i+j = s+t，说明两个皇后在同一对角线上。
            // 及 |i-s| = |j-t|
            if((arr[i] == arr[s]) || (Math.abs(i-s) == Math.abs(arr[i]-arr[s]))) {
                return false;
            }
        }

        return true;
    }

    // 棋盘为n*n，函数从第index行起求解皇后的布局，本函数初始调用为tria(1, n)
    public static void tria(int[] arr, int i, int n) {
        if(i >= n) {
            ++resultCount;
            System.out.println(Arrays.toString(arr));
        } else {
            // 依次生成各孩子结点
            for(int j = 0; j < n; j++) {
                // //第index行的皇后放入第j列
                arr[i] = j;
                if(place(arr, i)) {
                    tria(arr, i+1, n);
                    // 结点满足约束条件，则递归进入下一层继续遍历，否则跳过
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] queen = new int[4];
        tria(queen, 0, 4);

        System.out.println(resultCount);
    }


//    public static void main(String[] args) {
//        List<List<Integer>> listList = Backtracking.permute(new int[]{1, 2, 3});
//
//        System.out.println(listList.toString());
//    }
}
