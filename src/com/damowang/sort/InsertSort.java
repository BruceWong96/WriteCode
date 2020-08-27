package com.damowang.sort;

/**
 * TODO 插入排序
 * Created by wangzheng on 2020-08-25
 * TODO 算法描述
 * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
 *    1.从第一个元素开始，该元素可以认为已经被排序；
 *    2.取出下一个元素，在已经排序的元素序列中从后向前扫描；
 *    3.如果该元素（已排序）大于新元素，将该元素移到下一位置；
 *    4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 *    5.将新元素插入到该位置后；
 *    6.重复步骤2~5。
 *
 * TODO 时间复杂度分析：
 * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 *
 */
public class InsertSort {
    public static void insertSort(int[] num) {
        int len = num.length;
        if(len <= 1) {
            return;
        }

        int current = 0;
        for (int i = 0; i < len - 1; i++) {
            current = num[i + 1];
            int preIndex = i;

            //移动已排好序数组
            while(preIndex >= 0 && current < num[preIndex]) {
                num[preIndex + 1] = num[preIndex];
                preIndex--;
            }
            num[preIndex + 1] = current;
        }
    }
}
