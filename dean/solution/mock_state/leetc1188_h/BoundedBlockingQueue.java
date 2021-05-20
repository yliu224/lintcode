package mock_state.leetc1188_h;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {
    //注意多线程lock的用法
    ReentrantLock lock = new ReentrantLock();
    Deque<Integer> q;
    int capacity;
    public BoundedBlockingQueue(int capacity) {
        this.q = new LinkedList<>();
        this.capacity = capacity;
    }
    
    public void enqueue(int element) throws InterruptedException {
        while(q.size()==capacity){}
        lock.lock();
        try{
            q.add(element);
        } finally{
            lock.unlock();
        }
        
    }
    
    public int dequeue() throws InterruptedException {
        while(q.size()==0){}
        lock.lock();
        try{
            return q.poll();
        } finally{
            lock.unlock();
        }
    }
    
    public int size() {
        return q.size();
    }
}
