package com.damowang.sort;

/**
 * 计数排序
 * Created by wangzheng on 2020-08-25
 * 计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
 * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
 *
 * 计数排序(Counting sort)是一种稳定的排序算法。
 * 计数排序使用一个额外的数组C，其中第i个元素是待排序数组A中值等于i的元素的个数。
 * 然后根据数组C来将A中的元素排到正确的位置。它只能对整数进行排序。
 *
 * TODO 算法描述
 * 1.找出待排序的数组中最大和最小的元素；
 * 2.统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 * 3.对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 * 4.反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
 *
 * TODO 算法分析
 * 当输入的元素是n 个0到k之间的整数时，它的运行时间是 O(n + k)。计数排序不是比较排序，排序的速度快于任何比较排序算法。
 * 由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），这使得计数排序对于数据范围很大的数组，需要大量时间和内存。
 * 最佳情况：T(n) = O(n+k)  最差情况：T(n) = O(n+k)  平均情况：T(n) = O(n+k)
 */
public class CountingSort {
    //假设数组中存储的都是非负整数
    public static void countingSort(int[] num) {
        int len = num.length;
        if(len <= 1) {
            return;
        }

        //寻找数据范围
        int max = 0;
        for(int i = 0; i < len; i++) {
            if(max < num[i]) {
                max = num[i];
            }
        }

        //创建一个下标索引为 0 -> max 的数组
        int[] countArr = new int[max + 1];

        //计数
        for(int i = 0; i < len; i++) {
            countArr[num[i]] ++;
        }

        //临时数组，保存排序的结果
        int[] temp = new int[len];
        int j = len - 1;  //临时数组工作指针
        int countLen = countArr.length;
        //从后往前遍历计数数组，以保证排序的稳定性
        for(int i = countLen - 1; i >= 0; i--) {
            while (countArr[i] > 0) {
                temp[j] = i;
                countArr[i]--;
                j--;
            }
        }

        //临时数组中的结果复制给原数组
        for(int i = 0; i < len; i++) {
            num[i] = temp[i];
        }
    }
}
