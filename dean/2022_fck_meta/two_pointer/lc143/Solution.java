package two_pointer.lc143;

import datastructure.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public void reorderList(ListNode head) {
        List<ListNode> arrayNode = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            arrayNode.add(cur);
            cur = cur.next;
        }
        int l = 0, r = arrayNode.size() - 1;
        boolean switcher = true;
        while (l < r) {
            if (switcher) {
                arrayNode.get(l).next = arrayNode.get(r);
                l++;
                switcher = false;
            } else {
                arrayNode.get(r).next = arrayNode.get(l);
                r--;
                switcher = true;
            }
        }
        arrayNode.get(l).next = null;
    }
}
