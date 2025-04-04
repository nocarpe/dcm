package com.dcm.thread.alg;

public class ListNode {

    int val;
    ListNode next;

   public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode getNext(){
        return next;
    }
    public void setNext(ListNode node){
        this.next = node;
    }

    public int getVal(){
       return this.val;
    }
}