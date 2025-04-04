package com.dcm.thread.alg.d18;

/**
 * @author : yyyao
 * @date : 2024/12/4
 * @description :
 **/
public class Gupiao {

    //你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
    public int maxProfit(int[] prices) {
        int result = 0;
        int temp = Integer.MAX_VALUE;
        for (int price : prices) {
            temp = Math.min(temp, price);
            result = Math.max(result, price - temp);

        }
        return result;
    }

    //在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
    public int maxProfit2(int[] prices) {
        int result= 0;
        for (int i = 1; i < prices.length; i++) {
            result+=Math.max(0,prices[i]-prices[i-1]);

        }
        return result;
    }
}
