package com.dcm.thread.alg.d21;

import com.dcm.thread.alg.ListNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author : yyyao
 * @date : 2024/12/25
 * @description :
 **/
public class LianBiaoBetween {


    public ListNode reverseBtw(ListNode head, int left, int right) {
        //因为节点头有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.setNext(head);
        ListNode pre = dummyNode;

        for (int i = 0; i < left - 1; i++) {
            pre = pre.getNext();
        }
        ListNode cur = pre.getNext();
        ListNode next;

        for (int i = 0; i < right - left; i++) {
            next = cur.getNext();
            cur.setNext(next.getNext());
            next.setNext(pre.getNext());
            pre.setNext(next);
        }

        return dummyNode.getNext();
    }


    public ListNode reverseBtw2(ListNode head, int left, int right) {
        ListNode dumpNode = new ListNode(-1);
        dumpNode.setNext(head);
        ListNode pre = dumpNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.getNext();
        }
        ListNode rNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rNode = rNode.getNext();
        }
        //记录两端节点
        ListNode leftNode = pre.getNext();
        ListNode cur = rNode.getNext();
        //切断
        pre.setNext(null);
        rNode.setNext(null);
        reverseNode(leftNode);
        pre.setNext(rNode);
        leftNode.setNext(cur);
        return dumpNode.getNext();
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode last = headB;
        while (last.getNext() != null) {
            last = last.getNext();
        }
        last.setNext(headB);
        ListNode slow = headA;
        ListNode fast = headA;
        while (fast != null && fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                slow = headA;
                while (slow != fast) {
                    slow = slow.getNext();
                    fast = fast.getNext();
                }
                last.setNext(null);
                return slow;
            }
        }
        last.setNext(null);
        return null;
    }


    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> nodeList = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            nodeList.add(node);
            node = node.getNext();
        }
        int i = 0;
        int j = nodeList.size() - 1;
        while (i < j) {

            nodeList.get(i).setNext(nodeList.get(j));
            i++;
            if (i == j) {
                break;
            }
            nodeList.get(j).setNext(nodeList.get(i));
            j--;
        }
        nodeList.get(i).setNext(null);

    }

    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.getNext();
        mid.setNext(null);
        reorderList(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    public void mergeList(ListNode l1, ListNode l2) {
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


    public ListNode deleteDup(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.getNext() != null && cur.getNext().getNext() != null) {

            if (cur.getNext().getVal() == cur.getNext().getNext().getVal()) {
                int x = cur.getNext().getVal();
                while (cur.getNext() != null && cur.getNext().getVal() == x) {
                    cur.setNext(cur.getNext().getNext());
                }
            } else {
                cur = cur.getNext();
            }

        }
        return dummy.getNext();


    }


    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; ++i) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.getVal() < bPtr.getVal()) {
                tail.setNext(aPtr);
                aPtr = aPtr.getNext();
            } else {
                tail.setNext(bPtr);
                bPtr = bPtr.getNext();
            }
            tail = tail.getNext();
        }
        tail.setNext(aPtr != null ? aPtr : bPtr);

        return head.getNext();
    }


    PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(ListNode::getVal));

    public ListNode mergeKLists2(ListNode[] lists) {
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            ListNode s = pq.poll();
            cur.setNext(s);
            cur = cur.getNext();
            s = s.getNext();
            if (s != null) {
                pq.offer(s);
            }
        }

        return dummy.getNext();
    }


    public void reverseNode(ListNode node) {
        ListNode cur = node;
        ListNode pre = null;

        while (cur != null) {
            ListNode next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;
        }

    }


    public void reverseNode2(ListNode node) {
        ListNode cur = node;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;
        }
    }

}
