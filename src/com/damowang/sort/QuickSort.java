package com.damowang.sort;

/**
 * TODO 快速排序
 * Created by wangzheng on 2020-08-25
 * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，
 * 其中一部分记录的关键字均比另一部分的关键字小，
 * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 *
 * TODO 算法描述
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 * 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 *
 * TODO 算法分析
 * 最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(nlogn)　
 *
 */
public class QuickSort {
    public static void quickSort(int[] num) {
        int len = num.length;
        if(len <= 1) {
            return;
        }
        quickSortInternally(num, 0, len - 1);
    }

    private static void quickSortInternally(int[] num, int start, int end) {
        if(start >= end) {
            return;
        }
        int q = partition(num, start, end); //获得分区点
        quickSortInternally(num, start, q - 1);
        quickSortInternally(num, q + 1, end);
    }

    private static int partition(int[] num, int start, int end) {
        int pivot = num[end];
        int i = start;
        for(int j = start; j < end; j++) {
            if(num[j] < pivot) {
                swap(num, i, j);
                i++;
            }
        }
        swap(num, i, end);

        return i;
    }

    private static void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

}
