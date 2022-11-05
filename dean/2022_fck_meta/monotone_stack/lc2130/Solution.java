package monotone_stack.lc2130;

import datastructure.ListNode;

import java.util.Stack;

public class Solution {
    public int pairSum(ListNode head) {
        Stack<Integer> s = new Stack<>();
        int size = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            size++;
        }
        node = head;
        int index = 0;
        int max = -1;
        while (node != null) {
            if (index < size / 2) {
                s.push(node.val);
            } else {
                max = Math.max(max, s.pop() + node.val);
            }
            node = node.next;
            index++;
        }
        return max;
    }
}
