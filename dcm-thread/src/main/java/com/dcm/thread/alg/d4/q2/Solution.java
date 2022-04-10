package com.dcm.thread.alg.d4.q2;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 */
class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode check = head;
        ListNode next = null;
        int count = 0;
        int index = 0;
        while (count < k && check != null) {
            check = check.next;
            count++;
        }
        if (count == k) {
            while (index < k && cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
                index++;
            }
            if (next != null) {
                head.next = reverseKGroup(next, k);
            }
            return pre;
        } else {
            return head;
        }

    }
}