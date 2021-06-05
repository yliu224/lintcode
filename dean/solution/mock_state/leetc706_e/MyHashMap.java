package mock_state.leetc706_e;

import java.util.*;

class MyHashMap {
    private int[] hashMap;
    /** Initialize your data structure here. */
    public MyHashMap() {
        this.hashMap = new int[1000005];
        Arrays.fill(hashMap,-1);
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        this.hashMap[key]=value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return this.hashMap[key];
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        this.hashMap[key]=-1;
    }
}