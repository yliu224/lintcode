package dp.leetc562_e;

import java.util.Arrays;

public class Solution {
    public int longestLine(int[][] mat) {
        int lenI = mat.length;
        int lenJ = mat[0].length;
        int[][] dp = new int[lenI][lenJ];
        int max = 0;

        // top to bottom
        dp[0][0] = mat[0][0];
        for (int j = 1; j < lenJ; j++) {
            dp[0][j] = mat[0][j];
            max = Math.max(max, dp[0][j]);
        }
        for (int j = 0; j < lenJ; j++) {
            for (int i = 1; i < lenI; i++) {
                dp[i][j] = mat[i][j] == 0 ? 0 : dp[i - 1][j] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }

        // left to right
        for (int i = 0; i < lenI; i++) {
            Arrays.fill(dp[i], 0);
        }

        dp[0][0] = mat[0][0];
        for (int i = 1; i < lenI; i++) {
            dp[i][0] = mat[i][0];
            max = Math.max(max, dp[i][0]);
        }

        for (int i = 0; i < lenI; i++) {
            for (int j = 1; j < lenJ; j++) {
                dp[i][j] = mat[i][j] == 0 ? 0 : dp[i][j - 1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }

        // diagonal
        for (int i = 0; i < lenI; i++) {
            Arrays.fill(dp[i], 0);
        }
        for (int i = 0; i < lenI; i++) {
            dp[i][0] = mat[i][0];
            max = Math.max(max, dp[i][0]);
        }
        for (int j = 0; j < lenJ; j++) {
            dp[0][j] = mat[0][j];
            max = Math.max(max, dp[0][j]);
        }
        for (int i = 1; i < lenI; i++) {
            for (int j = 1; j < lenJ; j++) {
                dp[i][j] = mat[i][j] == 0 ? 0 : dp[i - 1][j - 1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }

        // anti-diagonal
        for (int i = 0; i < lenI; i++) {
            Arrays.fill(dp[i], 0);
        }
        for (int i = 0; i < lenI; i++) {
            dp[i][lenJ - 1] = mat[i][lenJ - 1];
            max = Math.max(max, dp[i][lenJ - 1]);
        }
        for (int j = 0; j < lenJ; j++) {
            dp[0][j] = mat[0][j];
            max = Math.max(max, dp[0][j]);
        }
        for (int i = 1; i < lenI; i++) {
            for (int j = lenJ - 2; j >= 0; j--) {
                dp[i][j] = mat[i][j] == 0 ? 0 : dp[i - 1][j + 1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}
