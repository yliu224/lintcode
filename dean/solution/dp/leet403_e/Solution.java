package dp.leet403_e;

import java.util.*;

public class Solution {
    public boolean canCross(int[] stones) {
        if(stones.length==2){//注意Edge case
            return stones[1]==1;
        }
        
        Map<Integer,Set<Integer>> steps = new TreeMap<>();
        
        for(int i=2;i<stones.length;i++){
            steps.put(stones[i],new HashSet<>());
        }
        
        steps.put(1,new HashSet<>(Arrays.asList(1)));
        for(Map.Entry<Integer,Set<Integer>> step:steps.entrySet()){
            int cur = step.getKey();
            for(int nextStep:step.getValue()){
                if(steps.containsKey(cur+nextStep)){
                    Set<Integer> s = steps.get(cur+nextStep);
                    s.add(nextStep);
                    steps.put(cur+nextStep,s); 
                }
                if(steps.containsKey(cur+nextStep+1)){
                    Set<Integer> s = steps.get(cur+nextStep+1);
                    s.add(nextStep+1);
                    steps.put(cur+nextStep+1,s); 
                }
                if(nextStep-1>0 && steps.containsKey(cur+nextStep-1)){
                    Set<Integer> s = steps.get(cur+nextStep-1);
                    s.add(nextStep-1);
                    steps.put(cur+nextStep-1,s); 
                }
            }
        }
        
        return !steps.get(stones[stones.length-1]).isEmpty();
    }
}
