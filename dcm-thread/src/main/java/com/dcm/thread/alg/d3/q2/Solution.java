package com.dcm.thread.alg.d3.q2;

/**
 * 基础排序法
 */
class Solution {

    //选择排序
    public int[] sortSelect(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int minIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = nums[i];
            nums[minIdx] = temp;
            nums[i] = nums[minIdx];
        }
        return nums;
    }


    // 插入排序：稳定排序，在接近有序的情况下，表现优异
    public int[] sortInsert(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int temp = nums[i];
            int j = i;
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
        return nums;

    }


    /**
     * 快排核心算法，递归实现
     */
    public static void sort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while (array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }

    public static void quickSort(int[] arr, int low, int high) {
        int t;
        if (low >= high) {
            return;
        }
        int i = low;
        int j = high;
        int temp = arr[i];
        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
            //最后将基准为与i和j相等位置的数字交换
            arr[low] = arr[i];
            arr[i] = temp;
            //递归调用左半数组
            quickSort(arr, low, j - 1);
            //递归调用右半数组
            quickSort(arr, j + 1, high);

        }

    }


    public static void mergeSortInOrder(int[] arr, int bgn, int mid, int end) {
        int l = bgn;
        int r = end;
        int m = mid;
        int i = 0;
        int[] arrs = new int[end - bgn + 1];
        while (l <= m && m <= r) {
            if (arr[l] < arr[m]) {
                arrs[i++] = arr[l++];
            } else {
                arrs[i++] = arr[m++];
            }
            while (l <= m) {
                arrs[i++] = arr[l++];
            }
            while (m <= r) {
                arrs[i++] = arr[m++];
            }
            for (int j = 0; j < arrs.length; j++) {
                arr[i + bgn] = arrs[i];
            }


        }

    }

    public static void mergeSort(int[] arr, int bgn, int end) {
        if (bgn >= end) {
            return;
        }
        int mid = (bgn + end) >> 1;
        mergeSort(arr, bgn, mid);
        mergeSort(arr, mid + 1, end);
        mergeSortInOrder(arr, bgn, mid, end);
    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void quickSortDemo(int arrs[], int left, int right) {
        if (left >= right) {
            return;
        }
        int temp;
        int i = left;
        int j = right;
        int base = arrs[i];
        while (i < j) {
            while (arrs[j] >= base && i < j) {
                j--;
            }
            while (arrs[i] <= base && i < j) {
                i++;
            }

            if (i < j) {
                temp = arrs[i];
                arrs[i] = arrs[j];
                arrs[j] = temp;
            }
            arrs[left] = arrs[i];
            arrs[i] = base;

            quickSortDemo(arrs, left, j - 1);
            quickSortDemo(arrs, j + 1, right);
        }


    }


    public static void main(String[] args) {
        int aarr[] = {5, 2, 7, 3, 8, 14, 11, 13, 9, 4};
        sort(aarr, 0, aarr.length - 1);

        for (int a : aarr) {
            System.out.print(a + ",");
        }
    }

}