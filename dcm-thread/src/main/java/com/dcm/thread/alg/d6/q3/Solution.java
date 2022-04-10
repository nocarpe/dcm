package com.dcm.thread.alg.d6.q3;

class Solution {

    public static int search(int[] nums, int target) {

        return binSearch(nums, 0, nums.length - 1, target);
    }


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

        if (left < middle) {
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


    public static int binSearch(int srcArray[], int start, int end, int key) {
        if (start >= end) {
            return -1;
        }
        int mid = (end - start) / 2 + start;
        if (srcArray[mid] == key) {
            return mid;
        }
        if (srcArray[mid] < key) {
            return binSearch(srcArray, mid + 1, end - 1, key);
        } else {
            return binSearch(srcArray, start + 1, mid - 1, key);
        }
    }


    public static int search2(int[] nums, int target) {

        return diff(nums, target, 0, nums.length - 1);

    }

    private static int diff(int[] nums, int target, int i, int j) {
        int x = nums[i];
        int y = nums[j];
        int len = (j - i) / 2 + i;
        int z = nums[len];
        if (x == target) {
            return i;

        }
        if (y == target) {
            return j;
        }
        if (z == target) {
            return len;
        }

        if (x < z) {
            if (z > target && x < target) {
                return diff(nums, target, i + 1, len - 1);
            } else {
                return diff(nums, target, len + 1, y - 1);
            }
        } else {
            if (z < target && y > target) {
                return diff(nums, target, len + 1, y - 1);
            } else {
                return diff(nums, target, i + 1, len - 1);
            }

        }

    }


}