package com.dcm.thread.alg.d26;

import com.dcm.thread.alg.ListNode;

/**
 * @author : yyyao
 * @date : 2025/1/26
 * @description :
 **/
public class PaLouti {


    public int climbStairs(int n) {

        int p = 0, q = 0, r = 1;

        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }


    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        for (int i = 2; i < length; i++) {
            cost[i] = cost[i] + Math.min(cost[i - 1], cost[i - 2]);
        }
        return Math.min(cost[length - 1], cost[length - 2]);

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(0);
        ListNode current = sentry;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            //求和
            int emp1 = (l1 != null) ? l1.getVal() : 0;
            int emp2 = (l2 != null) ? l2.getVal() : 0;
            int sum = emp1 + emp2 + carry;
            carry = sum / 10;
            ListNode newList = new ListNode(sum % 10);
            current.setNext(newList);
            current = current.getNext();

            if (l1 != null) {
                l1 = l1.getNext();
            }
            if (l2 != null) {
                l2 = l2.getNext();
            }

        }
        return sentry.getNext();

    }


}
