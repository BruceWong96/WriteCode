package com.damowang.sort;

/**
 * TODO 选择排序
 * Created by wangzheng on 2020-08-25
 * 算法描述
 * n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：
 * 初始状态：无序区为R[1..n]，有序区为空；
 * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
 * n-1趟结束，数组有序化了。
 *
 * 时间复杂度分析：
 * 最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 *
 */
public class SelectionSort {
    public static void selectionSort(int[] num) {
        int len = num.length;
        if(len <= 1) {
            return;
        }

        int temp = 0;
        for(int i = 0; i < len; i++) {
            int minIndex = i;
            for(int j = i + 1; j < len; j++) {
                if(num[j] < num[minIndex]){
                    minIndex = j;
                }
            }
            temp = num[minIndex];
            num[minIndex] = num[i];
            num[i] = temp;
        }
        return;
    }
}
