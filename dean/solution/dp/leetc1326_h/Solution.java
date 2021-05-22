package dp.leetc1326_h;

class Solution {
    // dp[t][r]有t个tap，覆盖r的范围最少需要几个tap
    // dp[t][r]= dp[t-1][r]!=0?dp[t-1][r]:dp[t][r-(ranges[t]*2)-1]+1
    public int minTaps(int n, int[] ranges) {
        int[][] dp = new int[2][n + 5];

        for (int i = 1; i <= n; i++) {
            dp[0][i] = i - 0 <= ranges[0] ? 1 : 0;
        }

        for (int t = 1; t < ranges.length; t++) {
            int cur = t % 2;
            int pre = (t + 1) % 2;

            for (int r = 1; r <= n; r++) {
                if (ranges[t] == 0) {//注意0这个special case
                    dp[cur][r] = dp[pre][r];
                    continue;
                }
                if (r - t <= ranges[t] && t - r < ranges[t]) {//注意理解题意，这个range和范围是错开的index。做完之后一定要print出来看一下
                    dp[cur][r] = t - ranges[t] >= 0 ? dp[pre][t - ranges[t]] + 1 : 1;
                    dp[cur][r] = Math.min(dp[pre][r] == 0 ? Integer.MAX_VALUE : dp[pre][r], dp[cur][r]);
                } else {
                    dp[cur][r] = dp[pre][r];
                }
            }
        }
        int last = (ranges.length - 1) % 2;

        for (int i = 1; i < ranges.length; i++) {
            if (dp[last][i] == 0)
                return -1;
        }

        return dp[last][n];
    }
}
