package dp.lc76_m;

public class Solution {
    /**
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    
     //记住这道题就用dp O(n2)的复杂度
     //leetc 300
    public int longestIncreasingSubsequence(int[] nums) {
        if(nums.length==0) return 0;
        int[] dp = new int[nums.length];
        int max = 1;
        dp[0]=1;
        for(int cur=1;cur<nums.length;cur++){
            for(int pre=0;pre<cur;pre++){
                //转移方程
                if(nums[cur]>nums[pre]){
                    dp[cur]=Math.max(dp[cur],dp[pre]+1);
                } else if(nums[cur]==nums[pre]){
                    dp[cur]=Math.max(dp[cur],dp[pre]);
                } else{
                    dp[cur]=Math.max(1,dp[cur]);//注意这儿是Max
                }
                max = Math.max(dp[cur],max);
            }
        }

        return max;
    }
}
