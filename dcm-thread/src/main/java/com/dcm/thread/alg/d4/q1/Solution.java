package com.dcm.thread.alg.d4.q1;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 */
class Solution {

    public static int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int n : nums) {
            heap.offer(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }


    public static void main(String[] args) {
int arr[] ={3,2,1,5,6,4};

System.out.println(findKthLargest(arr,2));
    }
}