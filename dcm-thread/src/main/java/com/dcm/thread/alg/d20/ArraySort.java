package com.dcm.thread.alg.d20;

/**
 * @author : yyyao
 * @date : 2024/12/15
 * @description :
 **/
public class ArraySort {

    public int[] sortInsert(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
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


    public void randomFastSort(int[] nums, int l, int r) {
        if (l > r) {
            return;
        }
        //base中的基准数
        int base = nums[l];
        int i = l, j = r;
        while (i != j) {
            //从右边开始往左边找，直到找到比base值小的数
            while (nums[j] >= base && i < j) {
                j--;
            }
            //再从左往右边找，直到找到比base值大的数
            while (nums[i] <= base && i < j) {
                i++;
            }
            if (i < j) {
                swapValue(nums, i, j);
            }
        }

        nums[l] = nums[i];
        nums[i] = base;
        randomFastSort(nums, l, j - 1);
        randomFastSort(nums, j + 1, r);
    }


    public void fastSortArray(int[] nums, int l, int r) {
        if (l > r) {
            return;
        }
        int base = nums[l];
        int i = l;
        int j = r;

        while (i != j) {

            while (j > i && nums[j] >= base) {
                j--;
            }

            while (j >i && nums[i] <= base) {
                i++;
            }
            if (i < j) {
                swapValue(nums, i, j);
            }

        }
      nums[l]=nums[i];
        nums[i]=base;
        fastSortArray(nums,l,j-1);
        fastSortArray(nums,j+1,r);

    }





    public static void swapValue(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
