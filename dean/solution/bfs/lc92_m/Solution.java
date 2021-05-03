package bfs.lc92_m;

import java.util.Arrays;

//0:1背包
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
     //dp[i][j]=dp[i-1][j-A[i]] || dp[i-1][j]
     public int backPack(int m, int[] A) {
        boolean[][] dp = new boolean[2][m+1];
        Arrays.fill(dp[0], false);
        Arrays.fill(dp[1], false);
        dp[0][0]=true;
        if(A[0]<=m){
            dp[0][A[0]]=true;
        }
        
        for(int i=1;i<A.length;i++){
            for(int j=0;j<=m;j++){
                int pre = (i+1)%2;
                int cur = i%2;
                dp[cur][j]=dp[pre][j];
                if(j-A[i]>=0){
                    dp[cur][j]=dp[cur][j] || dp[pre][j-A[i]];
                }
            }
        }

        for(int j=m;j>=0;j--){
            if(dp[(A.length+1)%2][j]){
                return j;
            }
        }
        return 0;
    }
}
