package dp.lc1447_m;

import java.util.Arrays;

import datastructure.Point;

public class Solution {
    private int MOD = 1000000007;

    /**
     * @param l:      The length of the matrix
     * @param w:      The width of the matrix
     * @param points: three points
     * @return: The sum of the paths sum
     */
    public long calculationTheSumOfPath(int l, int w, Point[] points) {
        //记住这个排序的写法 
        Arrays.sort(points,(a,b)->{
            if(a.x-b.x>0 && a.y-b.y>0){
                return 1;
            }
            return -1;
        });
        long point1 = findSolution(points[0].x, points[0].y);
        long point2 = findSolution(points[1].x - points[0].x + 1, points[1].y - points[0].y + 1);
        long point3 = findSolution(points[2].x - points[1].x + 1, points[2].y - points[1].y + 1);
        long end = findSolution(l - points[2].x + 1, w - points[2].y + 1);
        long pathSum = (((point1 * point2) % MOD * point3) % MOD * end) % MOD;//这儿是times 
        return (int) pathSum;
    }

    private long findSolution(int lenI, int lenJ) {
        if (lenI == 0 || lenJ == 0)
            return 1;
        // dp[i][j]=dp[i-1][j]+dp[i][j-1]
        long[][] dp = new long[2][lenJ];

        for (int i = 0; i < lenJ; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < lenI; i++) {
            for (int j = 0; j < lenJ; j++) {
                int cur = i % 2;
                int pre = (i + 1) % 2;
                if (j == 0) {
                    dp[cur][j] = dp[pre][j] % MOD;
                } else {
                    dp[cur][j] = (dp[pre][j] + dp[cur][j - 1]) % MOD;
                }
            }
        }
        return (dp[(lenI - 1) % 2][lenJ - 1]) % MOD;
    }
}
