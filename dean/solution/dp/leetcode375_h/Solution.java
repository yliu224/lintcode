package dp.leetcode375_h;

import java.util.*;

public class Solution {
    public int getMoneyAmount(int n) {
        //学习这个思想
        //dp[i][j] mean the min solution from i->j
        //dp[i][j]=Min(dp[i][k]+dp[k+1][j]+k)
        //            {k:j->i}
        //{i:0-n} {j:i->j}
        
        int[][] dp = new int[n+5][n+5];
        for(int i=0;i<dp.length;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        for(int i=0;i<dp.length;i++){
            dp[i][i]=0;
        }
        
        for(int j=2;j<=n;j++){
            for(int i=j-1;i>=1;i--){
                for(int k=j;k>=i;k--){
                    if(k==j){
                        dp[i][j]=Math.min(dp[i][j],dp[i][k-1]+k);
                    }else if(k==i){
                        dp[i][j]=Math.min(dp[i][j],dp[k+1][j]+k);
                    } else{
                        dp[i][j]=Math.min(dp[i][j],dp[i][k-1]+dp[k+1][j]+k);
                    }
                }
            }
            System.out.println();
        }
        
        return dp[1][n];
    }
}
