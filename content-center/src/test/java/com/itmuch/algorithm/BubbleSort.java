package com.itmuch.algorithm;

/**
 * 冒泡排序
 */
public class BubbleSort {

    /**
     * 冒泡算法
     * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     *
     */
    public static int[] bubbleSort(int[] array) {
        // 用来计算请求时间哒
        if (array.length == 0)
            return array;
        // 记录最后一次交换的位置
        int lastExchange = 0;
        // 无序数组的边界
        int sortBorder = array.length - 1;
        int length = array.length - 1;

            for (int i = 0; i < length; i++) {
                // 优化点1： 假如某一趟排序之后已经有序，我们需要减少排序的趟数。否则就做了很多无用功。
                boolean flag = false;
                for (int j = 0; j < sortBorder; j++) {
                    if ( array[j] > array[j+1] ){
                        flag = true;
                        int temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                        // 优化点2：对数组有界区进行界定 更新为最后一次交换位置
                        lastExchange = j;
                    }
                }
                sortBorder = lastExchange;
                if ( !flag ){
                    return array;
                }

            }

        return array;
    }

}
