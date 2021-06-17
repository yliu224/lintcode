package dp.leetc568_m;

import java.util.*;

public class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int lenI = flights.length;
        int lenJ = flights[0].length;

        int[][] dp = new int[lenI + 5][lenJ + 5];

        for (int i = 0; i < lenI; i++) {
            Arrays.fill(dp[i], -1);
            if (flights[0][i] == 1) {
                dp[i][0] = days[i][0];
            }
        }
        dp[0][0] = days[0][0];

        // dp[i][j] max vaccation day in city i on week j
        for (int j = 1; j < days[0].length; j++) {// day j
            for (int i = 0; i < lenI; i++) {// city i
                for (int c = 0; c < lenI; c++) {// city c
                    if (c == i && dp[i][j - 1] != -1) {
                        dp[i][j] = Math.max(dp[i][j - 1] + days[i][j], dp[i][j]);
                    }
                    if (flights[c][i] == 1 && dp[c][j - 1] != -1) {
                        dp[i][j] = Math.max(dp[c][j - 1] + days[i][j], dp[i][j]);
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < lenI; i++) {
            max = Math.max(dp[i][days[0].length - 1], max);
        }

        return max;
    }
}
