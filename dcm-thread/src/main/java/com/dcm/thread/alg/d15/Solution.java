package com.dcm.thread.alg.d15;

/**
 * @author : yyyao
 * @date : 2024/4/6
 * @description :
 **/
public class Solution {


    //股票买卖 3,4,9,1,5
    public int maxProfit(int[] prices) {
        int result = 0;
        int cost = Integer.MAX_VALUE;

        for (int price : prices) {
            cost = Math.min(cost, price);
            result = Math.max(result, price - cost);
        }
        return result;
    }
}
