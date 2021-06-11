package dp.leetc120_e;

import java.util.*;

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // dp[i][j]=Math.min(dp[i-1][j]+triangle[i][j],dp[i-1][j-1]+triangle[i][j])

        if (triangle.size() == 1)
            return triangle.get(0).get(0);
        int[][] dp = new int[205][205];
        int rowLen = triangle.size();

        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int preRowLen = triangle.get(i).size() - 1;
                dp[i][j] = Integer.MAX_VALUE;
                if (j != preRowLen) {
                    dp[i][j] = Math.min(dp[i][j], triangle.get(i).get(j) + dp[i - 1][j]);
                }
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], triangle.get(i).get(j) + dp[i - 1][j - 1]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.get(rowLen - 1).size(); i++) {
            min = Math.min(min, dp[rowLen - 1][i]);
        }

        return min;
    }
}
