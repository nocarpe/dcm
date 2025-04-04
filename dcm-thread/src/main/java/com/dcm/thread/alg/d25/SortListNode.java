package com.dcm.thread.alg.d25;

import com.dcm.thread.alg.ListNode;
import java.util.List;

/**
 * @author : yyyao
 * @date : 2025/1/18
 * @description :
 **/
public class SortListNode {

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (head ==null){
            return head;
        }

        if(head.getNext()==tail){
            head.setNext(null);
            return head;
        }

        ListNode slow =head,fast=head;
        while(fast!=tail){
            slow=slow.getNext();
            fast=fast.getNext();
            if(fast!=tail){
                fast=fast.getNext();
            }
        }
        ListNode mid =slow;
        ListNode l1 =sortList(head,mid);
        ListNode l2 = sortList(mid,tail);
        return mergeNode(l1,l2);



    }


    public ListNode mergeNode(ListNode head, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        ListNode l1 = head;
        ListNode l2 = head2;
        while (l1 != null && l2 != null) {
            if (l1.getVal() > l2.getVal()) {
                temp.setNext(l2);
                l2 = l2.getNext();
            } else {
                temp.setNext(l1);
                l1 = l1.getNext();
            }
            temp = temp.getNext();
        }
        if (l1 != null) {
            temp.setNext(l1);
        } else if (l2 != null) {
            temp.setNext(l2);
        }
        return dummy.getNext();
    }
}
