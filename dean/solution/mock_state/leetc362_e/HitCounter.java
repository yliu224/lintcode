package mock_state.leetc362_e;

import java.util.*;

class HitCounter {

    /** Initialize your data structure here. */
    private Deque<Integer> q;
    public HitCounter() {
        this.q = new LinkedList<>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        q.add(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!q.isEmpty()){
            if(timestamp-q.peek()>=300){
                q.poll();
            } else{
                break;
            }
        }
        return q.size();
    }
}
