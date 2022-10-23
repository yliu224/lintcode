package linked_list.lc146;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private int capacity;
    private Map<Integer, Node> cache = new HashMap<>();
    ;
    private Node root = new Node();
    private Node tail = new Node();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        root.child = tail;
        tail.parent = root;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            moveToRoot(n);
            return n.value;
        } else {
            return -1;
        }
    }


    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            n.value = value;
            moveToRoot(n);
            return;
        }

        Node n = new Node();
        n.value = value;
        n.key = key;
        cache.put(key, n);

        n.child = root.child;
        n.parent = root;

        root.child.parent = n;
        root.child = n;

        if (cache.size() > capacity) {
            Node lastNode = tail.parent;
            lastNode.parent.child = tail;
            tail.parent = lastNode.parent;
            cache.remove(lastNode.key);
        }
    }

    private void moveToRoot(Node n) {
        n.parent.child = n.child;
        n.child.parent = n.parent;

        root.child.parent = n;
        n.child = root.child;

        n.parent = root;
        root.child = n;
    }

    class Node {
        int key;
        int value;
        Node parent;
        Node child;
    }
}
