package com.damowang.sort;

/**
 * TODO 归并排序
 * Created by wangzheng on 2020-08-25
 * 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是O(n log n）的时间复杂度。
 * 代价是需要额外的内存空间。
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；
 * 即先使每个子序列有序，再使子序列段间有序。
 * 若将两个有序表合并成一个有序表，称为2-路归并。
 *
 * TODO 算法描述：
 * 把长度为n的输入序列分成两个长度为n/2的子序列；
 * 对这两个子序列分别采用归并排序；
 * 将两个排序好的子序列合并成一个最终的排序序列。
 * TODO 算法分析
 * 最佳情况：T(n) = O(n)  最差情况：T(n) = O(nlogn)  平均情况：T(n) = O(nlogn)
 *
 */
public class MergeSort {
    public static void mergeSort(int[] num) {
        int len = num.length;
        if(len <= 1) {
            return ;
        }
        mergeSortInternally(num, 0, len - 1);
    }

    //递归调用函数
    private static void mergeSortInternally(int[] num, int start, int end) {
        //terminator
        if (start >= end) {
            return ;
        }

        //process
        //取中间位置，防止溢出
        int mid = start + ((end - start) >> 1);

        //drill down
        mergeSortInternally(num, start, mid);
        mergeSortInternally(num, mid + 1, end);

        //将排序好的左右子序列合并
        merge(num, start, mid, end);
    }

    private static void merge(int[] num, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int res = 0;     //临时数组工作指针
        int[] temp = new int[end - start + 1];

        //合并
        while (i <= mid && j <= end) {
            if(num[i] <= num[j]) {
                temp[res++] = num[i++];
            }else {
                temp[res++] = num[j++];
            }
        }

        //判断那个数组中有剩余数据
        int p = i;
        int q = mid;
        if(j <= end) {
            p = j;
            q = end;
        }

        //将剩余数据加入数组
        while (p <= q){
            temp[res++] = num[p++];
        }

        //将临时数组数据拷贝会结果数组
        for(int m = 0; m < end - start + 1; m++) {
            num[start + m] = temp[m];
        }
    }
}
