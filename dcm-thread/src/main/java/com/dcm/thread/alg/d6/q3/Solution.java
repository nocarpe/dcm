package com.dcm.thread.alg.d6.q3;

class Solution {


    public static int binSearch2(int srcArray[], int start, int end, int key) {
        if (start > end) {
            return -1;
        }
        int mid = (end - start) / 2 + start;
        int left = srcArray[start];
        int right = srcArray[end];
        int middle = srcArray[mid];
        if (left == key) {
            return start;
        }
        if (right == key) {
            return end;
        }
        if (middle == key) {
            return mid;
        }

        if (start < end) {
            if (left < key && middle > key) {
                return binSearch2(srcArray, start + 1, mid - 1, key);
            } else {
                return binSearch2(srcArray, mid + 1, end - 1, key);
            }

        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int arrs[] = {4, 5, 6, 7, 8, 9, 1, 2, 3};
        System.out.println(search(arrs, 1));
    }

    public static int search(int[] nums, int target) {

        return binSearch2(nums, 0, nums.length - 1, target);
    }


    public static int search2(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = (j - i) / 2 + i;
            int num = nums[mid];
            if (num == target) {
                return mid;
            }
            if (num > target) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }

        }
        return -1;
    }

}