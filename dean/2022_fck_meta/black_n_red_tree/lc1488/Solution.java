package black_n_red_tree.lc1488;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution {
    public int[] avoidFlood(int[] rains) {
        Map<Integer,Integer> fullLakes = new HashMap<>();
        TreeSet<Integer> drain = new TreeSet<>();
        int[] ans = new int[rains.length];
        for(int i=0;i<rains.length;i++){
            if(rains[i]==0){
                drain.add(i);
                ans[i]=1;
            } else{
                if(fullLakes.containsKey(rains[i])){
                    Integer drainDay = drain.higher(fullLakes.get(rains[i]));
                    if(drainDay==null) return new int[0];
                    ans[drainDay]=rains[i];
                    //放水的时候，要把水给放掉
                    drain.remove(drainDay);
                }
                //注意每次放了水，水有是满的
                fullLakes.put(rains[i],i);
                ans[i]=-1;
            }
        }
        return ans;
    }
}
