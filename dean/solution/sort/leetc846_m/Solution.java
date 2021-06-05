package sort.leetc846_m;

import java.util.*;

public class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer,Integer> list = new TreeMap<>();//注意TreeSet的用法
        
        for(int i=0;i<hand.length;i++){
            int count = list.getOrDefault(hand[i],0);
            list.put(hand[i],count+1);
        }
        
        while(list.size()!=0){
            int start = list.keySet().iterator().next();   
            for(int i=0;i<groupSize;i++){
                int count = list.getOrDefault(start, -1);
                if(count!=-1){
                    count--;
                    if(count==0){
                        list.remove(start);
                    } else{
                        list.put(start,count);
                    }
                } else{
                    return false;
                }
                start++;
            }
        }
        
        return true;
    }
}
