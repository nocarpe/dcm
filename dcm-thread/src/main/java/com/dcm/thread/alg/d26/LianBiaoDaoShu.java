package com.dcm.thread.alg.d26;

import com.dcm.thread.alg.ListNode;

/**
 * @author : yyyao
 * @date : 2025/1/26
 * @description :
 **/
public class LianBiaoDaoShu {

    public ListNode trainingPlan(ListNode head, int cnt) {
        ListNode former = head, latter = head;

        for (int i = 0; i < cnt; i++) {
            former = former.getNext();
        }
        while (former != null) {
            former = former.getNext();
            latter = latter.getNext();

        }
        return latter;
    }
}
