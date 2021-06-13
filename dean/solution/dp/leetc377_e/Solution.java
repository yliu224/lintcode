package dp.leetc377_e;

public class Solution {
    public int combinationSum4(int[] nums, int target) {
        // dp[i]=sum(dp[i-nums[j]])
        // {j:0->nums.length}

        int[] dp = new int[target + 5];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }

        return dp[target];
    }
}
