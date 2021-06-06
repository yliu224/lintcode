package binary_search.leetc708_m;

public class Solution {
    class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node n = new Node(insertVal);
            n.next = n;
            return n;
        }
        Node node = head;
        int cur = node.val;
        int next = node.next.val;

        while (node.next != head) {// 注意这儿应该用reference
            // 注意下面这个判断
            if ((insertVal >= cur && insertVal <= next) || (cur > next && (insertVal >= cur || insertVal <= next))) {
                Node newNode = new Node(insertVal, node.next);
                node.next = newNode;
                return head;
            }
            node = node.next;
            cur = node.val;
            next = node.next.val;
        }

        Node newNode = new Node(insertVal);
        newNode.next = node.next;
        node.next = newNode;

        return head;
    }
}
