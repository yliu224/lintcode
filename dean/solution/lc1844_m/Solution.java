package dean.solution.lc1844_m;

import java.util.HashMap;
import java.util.Map;

//PrefixSum + HashMap
public class Solution{
    public int subarraySumEqualsKII(int[] nums, int k) {
        if(nums == null) return -1;
        if(nums.length==0) return -1;
        int[] prefixSum = getPrefixSum(nums);
        Map<Integer,Integer> sum2Index = new HashMap<>();
        int min = Integer.MAX_VALUE;
        
        //PrefixSum[end]-PrefixSum[start] = k
        //PrefixSum[start] = PrefixSum[end] - k
        sum2Index.put(prefixSum[0],-1);
        for(int end=0;end<nums.length;end++){
            if(sum2Index.containsKey(prefixSum[end+1]-k)){
                min = Math.min(end-sum2Index.get(prefixSum[end+1]-k),min);
            }
            sum2Index.put(prefixSum[end+1],end);
        }

        return min==Integer.MAX_VALUE?-1:min;
    }

    //Template
    public int[] getPrefixSum(int[] nums){
        int[] prefixSum = new int[nums.length+1];

        prefixSum[0]=0;
        for(int i=0;i<nums.length;i++){
            prefixSum[i+1]=prefixSum[i]+nums[i];
        }

        return prefixSum;
    }
}