package dp.leetc560_h;

import java.util.*;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        //sum[i]表示0-i的和
        //sum[j]-sum[i]表示i->j的和
        //sum[j]-sum[i]=k 表示i->j的和等于k
        //sum[j]-k=sum[i]，所以我们只需要知道sum[i]出现的次数，就可以知道，当index==j的时候，sum==k的次数
        //dp[i]=count(sum[j]-k) --> sum[j]-k 出现的次数 b 
        Map<Integer,Integer> sumMap = new HashMap<>();
        int count = 0;
        int sum = 0;
        sumMap.put(0,1);
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            count+=sumMap.getOrDefault(sum-k, 0);
            sumMap.put(sum,sumMap.getOrDefault(sum, 0)+1);
        }
        return count;
    }
}
