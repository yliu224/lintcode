package dp.lc718;

public class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        //dp[i][j] 表示，以i和j作为开始，能匹配到的最长substring的长度
        // if(nums1[i]==nums2[j]) dp[i][j] = dp[i+1][j+1]+1
        int max = 0;
        for (int i = nums1.length - 1; i >= 0; i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    if (i + 1 == nums1.length || j + 1 == nums2.length) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i + 1][j + 1] + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
        }
        return max;
    }
}
