package dp.lc1155;

import java.util.Arrays;

public class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        int mod = 1000000007;
        int[] tt1 = new int[target + 1];
        int[] tt2 = new int[target + 1];

        //这儿需要Math.min()
        for (int i = 1; i <= Math.min(k, target); i++) {
            tt1[i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int kk = 1; kk <= k; kk++) {
                //这儿是  i * K
                for (int j = i; j <= Math.min(i * k, target); j++) {
                    if (j - kk < 1) {
                        //这儿需要continue
                        continue;
                    }
                    tt2[j] = (tt2[j] % mod + tt1[j - kk] % mod) % mod;
                }
            }
            int[] tmp = tt1;
            tt1 = tt2;
            tt2 = tmp;
            //注意这儿需要fill 0
            Arrays.fill(tt2, 0);
        }
        return tt1[target];
    }
}
