package com.damowang.sort;

/**
 * TODO 冒泡排序
 * Created by wangzheng on 2020-08-25
 * TODO 算法描述
 * 1.比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 3.针对所有的元素重复以上的步骤，除了最后一个；
 * 4.重复步骤1~3，直到排序完成。
 *
 * TODO 算法分析：
 * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 *
 */
public class BubbleSort {
    public static void bubbleSort(int[] num) {
        int len = num.length;
        if(len <= 1) {
            return;
        }

        int temp = 0;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len - 1 - i; j++) {
                if(num[j] > num[j + 1]) {
                    temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                }
            }
        }
    }
}
