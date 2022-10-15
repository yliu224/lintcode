package two_pointer.lc142;

import datastructure.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    private Set<ListNode> visited = new HashSet<>();

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (visited.contains(cur)) {
                return cur;
            }
            visited.add(cur);
            cur = cur.next;
        }
        return null;
    }
}
