package mock_state.leetc146_m;

import java.util.*;

class LRUCache {

    //数据结构的使用 
    private LinkedHashSet<Integer> operationQ;
    private int capacity;
    private Map<Integer, Integer> cache;
    public LRUCache(int capacity) {
        this.operationQ = new LinkedHashSet<>();
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }
    
    public int get(int key) {
        addOperation(key);
        return this.cache.getOrDefault(key,-1);
    }
    
    public void put(int key, int value) {
        addOperation(key);
        if(this.cache.size()==this.capacity && !this.cache.containsKey(key)){
            while(!this.operationQ.isEmpty()){
                int top = this.operationQ.stream().findFirst().get();
                this.operationQ.remove(top);
                if(this.cache.containsKey(top)){
                    this.cache.remove(top);
                    break;
                }
            }
        }
        
        this.cache.put(key,value);
    }
    
    private void addOperation(int key){
        if(this.operationQ.contains(key)){
            this.operationQ.remove(key);
        }
        this.operationQ.add(key);
    }
}
