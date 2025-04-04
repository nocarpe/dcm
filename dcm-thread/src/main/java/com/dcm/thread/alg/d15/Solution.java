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




















    // 3,5,12,1,6,7
    public  int maxShouyi(int[] prices){

        int result =0;
        int cost = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length-1; i++) {
            cost = Math.min(cost, prices[i]);

           result = Math.max(result,prices[i]-cost);


        }

return result;

    }
}
