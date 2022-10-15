package two_pointer.lc86;

import datastructure.ListNode;

public class Solution {
     public class ListNode {
         int val;
         ListNode next;

         ListNode() {
         }

         ListNode(int val) {
             this.val = val;
         }

         ListNode(int val, ListNode next) {
             this.val = val;
             this.next = next;
         }
     }

    public ListNode partition(ListNode head, int x) {
        ListNode lessThanHead = new ListNode();
        ListNode lessThanTail = lessThanHead;

        ListNode greaterThanHead = new ListNode();
        ListNode greaterThanTail = greaterThanHead;

        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                lessThanTail.next = cur;
                lessThanTail = cur;
            } else {
                greaterThanTail.next = cur;
                greaterThanTail = cur;
            }
            cur = cur.next;
        }
        lessThanTail.next = greaterThanHead.next;
        greaterThanTail.next = null;
        return lessThanHead.next;
    }
}
