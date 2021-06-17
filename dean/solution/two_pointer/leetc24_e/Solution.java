package two_pointer.leetc24_e;

import datastructure.*;

public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;

        ListNode newHead = head.next;

        ListNode cur = head;
        ListNode next = head.next;

        while (cur != null && next != null) {
            ListNode nextCur = next.next;
            ListNode nextNext = nextCur == null ? null : nextCur.next;
            next.next = cur;
            cur.next = nextNext == null ? nextCur : nextNext;
            cur = nextCur;
            next = nextNext;
        }

        return newHead;
    }
}
