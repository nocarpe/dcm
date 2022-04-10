package com.dcm.thread.alg.d6.q1;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode(0);
        ListNode temp = result;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                temp = temp.next;
                list1 = list1.next;
            } else {
                temp.next = list2;
                temp = temp.next;
                list2 = list2.next;
            }

        }
        //判断空
        if (list1 == null) {
            temp.next = list2;
        } else {
            temp.next = list1;
        }
        return result.next;

    }


    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists2(list1, list2.next);
            return list2;
        }

    }

}