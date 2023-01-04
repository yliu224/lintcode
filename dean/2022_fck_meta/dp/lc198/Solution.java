package dp.lc198;

import java.util.Arrays;

public class Solution {
    public int rob(int[] nums) {
        if (nums.length <= 2) {
            return Arrays.stream(nums).max().getAsInt();
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
