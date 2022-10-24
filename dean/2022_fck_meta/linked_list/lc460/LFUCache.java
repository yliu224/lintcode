package linked_list.lc460;

import java.util.HashMap;
import java.util.Map;

//一定要把步骤细分好！！
public class LFUCache {
    private Map<Integer, Node> cache = new HashMap<>();
    private Map<Integer, Node> counter = new HashMap<>();
    private Node root, tail;
    private int capacity, size;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.root = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.root.child = tail;
        this.tail.parent = root;
        this.size = 0;
    }

    public int get(int key) {
        if (!cache.containsKey(key) || capacity==0) {
            return -1;
        }
        Node n = cache.get(key);
        remove(n);
        n.count++;
        Node front, end;
        if (counter.containsKey(n.count)) {
            front = counter.get(n.count).parent;
            end = counter.get(n.count);
        } else if (counter.containsKey(n.count - 1)) {
            front = counter.get(n.count - 1).parent;
            end = counter.get(n.count - 1);
        } else {
            front = n.parent;
            end = n.child;
        }
        insert(front, n, end);
        return n.value;
    }

    public void put(int key, int value) {
        if(capacity==0) return;
        if (cache.containsKey(key)) {
            cache.get(key).value = value;
            get(key);
            return;
        }
        //仔细读题！！
        if(size == capacity){
            Node r = tail.parent;
            remove(r);
            cache.remove(r.key);
            size--;
        }

        Node n = new Node(key, value);
        cache.put(key, n);

        Node front,end;
        if(counter.containsKey(1)){
            front = counter.get(1).parent;
            end = counter.get(1);
        } else{
            front = tail.parent;
            end = tail;
        }
        insert(front,n,end);
        size++;
    }

    private void remove(Node n) {
        if (counter.get(n.count) == n) {
            Node next = n.child;
            if (next.count == n.count) {
                counter.put(next.count, next);
            } else {
                counter.remove(n.count);
            }
        }
        n.parent.child = n.child;
        n.child.parent = n.parent;
    }

    private void insert(Node a, Node b, Node c) {
        a.child = b;
        b.parent = a;

        c.parent = b;
        b.child = c;

        counter.put(b.count, b);
    }


    class Node {
        int count;
        int value;
        int key;
        Node parent;
        Node child;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }
}
