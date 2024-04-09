package com.dcm.thread.alg.d3.q1;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public ListNode reverseList(ListNode head) {

        ListNode cur = head;
        ListNode pre = null;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;

    }

    public ListNode reverseList1(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next =pre;
            pre=cur;
            cur= temp;
        }

        return pre;
    }

}