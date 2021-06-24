package dp.leetc1277_e;

public class Solution {
    public int countSquares(int[][] matrix) {
        int lenI = matrix.length;
        int lenJ = matrix[0].length;

        int[][] dp = new int[lenI][lenJ];

        for (int i = 0; i < lenI; i++) {
            if (i == 0) {
                for (int j = 0; j < lenJ; j++) {
                    dp[i][j] = matrix[i][j];
                }
            } else {
                dp[i][0] = matrix[i][0];
            }
        }

        for (int i = 1; i < lenI; i++) {
            for (int j = 1; j < lenJ; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < lenI; i++) {
            for (int j = 0; j < lenJ; j++) {
                sum += dp[i][j];
            }
        }

        return sum;
    }
}
