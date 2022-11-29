package linked_list.lc19;

import datastructure.ListNode;

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        int len = getLen(head);
        int moveCount = len - n;
        int index = 0;
        ListNode cur = newHead;
        while (index < moveCount) {
            cur = cur.next;
            index++;
        }
        cur.next = cur.next.next;
        return newHead.next;
    }

    int getLen(ListNode node) {
        int n = 0;
        while (node != null) {
            node = node.next;
            n++;
        }
        //System.out.println(n);
        return n;
    }
}
