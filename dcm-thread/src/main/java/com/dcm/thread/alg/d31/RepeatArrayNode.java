package com.dcm.thread.alg.d31;

import com.dcm.thread.alg.ListNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author : yyyao
 * @date : 2025/6/18
 * @description :
 **/
public class RepeatArrayNode {

    /**
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     *
     * L0 → L1 → … → Ln - 1 → Ln
     * 请将其重新排列后变为：
     *
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */

    public void reorderList(ListNode head) {
        //1. 找到链表中位节点
        //2.反转从中位开始后的节点
        //3.合并两个链表

        ListNode mid = getMid(head);
        ListNode l1 = head;
        ListNode l2 = mid.getNext();
        mid.setNext(null);
        l2 = revertNode(l2);
        crossMerge(l1, l2);
    }

    public ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    public ListNode revertNode(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        while (cur != null) {
            ListNode temp = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public void crossMerge(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.getNext();
            l2_tmp = l2.getNext();

            l1.setNext(l2);
            l1 = l1_tmp;
            l2.setNext(l1);
            l2 = l2_tmp;
        }

    }


    public void reorderList2(ListNode head) {

        List<ListNode> list = new ArrayList<>();

        while (head != null) {
            list.add(head.getNext());
            head = head.getNext();
        }

        int i = 0;
        int j = list.size() - 1;

        while (i != j) {

            list.get(i).setNext(list.get(j));
            i++;
            if (i == j) {
                break;
            }
            list.get(j).setNext(list.get(i));
            j--;
        }
        list.get(i).setNext(null);

    }


    PriorityQueue<ListNode> queue = new PriorityQueue<>((Comparator.comparingInt(ListNode::getVal)));

    public ListNode mergeKLists(ListNode[] lists) {

        //校验空值
        for (ListNode node : lists) {
            queue.offer(node);
        }
        ListNode dump = new ListNode();
        ListNode cur = dump;

        while (!queue.isEmpty()) {
            ListNode tmp = queue.poll();
            cur.setNext(tmp);
            cur = cur.getNext();
            tmp = tmp.getNext();
            if (tmp != null) {
                queue.offer(tmp);
            }


        }

        return dump.getNext();

    }


}
