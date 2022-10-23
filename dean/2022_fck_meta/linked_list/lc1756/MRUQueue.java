package linked_list.lc1756;

public class MRUQueue {
    private Node root = new Node(-1);
    private Node tail = new Node(-1);

    public MRUQueue(int len) {
        root.child = tail;
        tail.parent = root;
        for (int i = 1; i <= len; i++) {
            Node n = new Node(i);
            insert(tail.parent, n, tail);
        }
    }

    public int fetch(int k) {
        Node n = root;
        for (int i = 0; i < k; i++) {
            n = n.child;
        }
        remove(n);
        insert(tail.parent, n, tail);
        return n.value;
    }
    //定义这两个Helper function 很有用
    private void remove(Node n) {
        n.parent.child = n.child;
        n.child.parent = n.parent;
    }
    //定义这两个Helper function 很有用
    private void insert(Node a, Node b, Node c) {
        a.child = b;
        c.parent = b;
        b.parent = a;
        b.child = c;
    }

    class Node {
        int value;
        Node parent;
        Node child;

        Node(int value) {
            this.value = value;
        }
    }
}
