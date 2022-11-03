package linked_list.lc138;

import datastructure.Node;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private Map<Node, Node> originalToNew = new HashMap<>();
    private Map<Node, Node> newToOriginal = new HashMap<>();

    public Node copyRandomList(Node head) {
        Node tmpRoot = new Node(0);
        Node cur = head;
        Node newCur = tmpRoot;
        while (cur != null) {
            Node n = new Node(cur.val);
            originalToNew.put(cur, n);
            newToOriginal.put(n, cur);
            newCur.next = n;
            newCur = n;
            cur = cur.next;
        }

        newCur = tmpRoot.next;
        while (newCur != null) {
            Node orig = newToOriginal.get(newCur);
            if (orig.random == null) {
                newCur.random = null;
            } else {
                newCur.random = originalToNew.get(orig.random);
            }
            newCur = newCur.next;
        }

        return tmpRoot.next;
    }
}
