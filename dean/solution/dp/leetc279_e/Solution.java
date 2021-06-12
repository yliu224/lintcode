package dp.leetc279_e;

import java.util.*;

public class Solution {
    public int numSquares(int n) {
        // squareIndex = squares[squareIndex+1]<=i?squareIndex+1:squareIndex;
        // dp[i]=Math.min(dp[i-squares[j]]+1,dp[i])
        // {j:0-squareIndex}
        int[] squares = new int[105];
        for (int i = 1; i < 105; i++) {
            squares[i] = i * i;
        }

        int[] dp = new int[10005];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 1;
        dp[0] = 0;
        int squareIndex = 1;
        for (int i = 2; i < 10005; i++) {
            squareIndex = squares[squareIndex + 1] <= i ? squareIndex + 1 : squareIndex;
            for (int j = 1; j <= squareIndex; j++) {
                dp[i] = Math.min(dp[i - squares[j]] + 1, dp[i]);
            }
        }
        return dp[n];
    }
}
