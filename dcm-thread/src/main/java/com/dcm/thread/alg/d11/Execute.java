package com.dcm.thread.alg.d11;

/**
 * @author : yyyao
 * @date : 2023/10/17
 * @description :
 **/
public class Execute {

    public static void main(String[] args) {
            int[] arr=new int[]{1,3,-1,-3,6,4,5,3,9};
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        maxSlidingWindow.maxSlidingWindow3(arr,3);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }

    }

}
