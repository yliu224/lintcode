package dp.leetc221_e;

public class Solution {
    public int maximalSquare(char[][] matrix) {
        // dp[i][j] means the largest square starting from i,j
        //
        // neighbors = dp[i-1][j],dp[i-1][j-1],dp[i-1][j-1];
        // dp[i][j]=matrix[i][j]==0?0:{anyOf(neighbors)==0?1:square(sqrt(min(neighbors)+1))}

        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = Math.max(max, dp[i][0]);
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                max = Math.max(max, dp[0][i]);
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    if (dp[i - 1][j - 1] == 0 || dp[i - 1][j] == 0 || dp[i][j - 1] == 0) {
                        dp[i][j] = 1;
                    } else {
                        int minLen = (int) Math.sqrt(Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]));
                        dp[i][j] = (minLen + 1) * (minLen + 1);
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}
