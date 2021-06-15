package dp.leetc486_hh;

import java.util.*;

public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        //学习解法
        //DP写不出来的时候，先写DFS
        //dfs(array){当前这个数组，我能够拿到的最大值
        //  Math.max(
        //           left+dfs(Array-left),//我拿最左边，他拿最左边
        //           left+dfs(Array-right),//我拿最左边，他拿最右边
        //           right+dfs(Array-left),//我拿最右边，他拿最左边
        //           right+dfs(Array-right)//我拿最右边，他拿最右边
        //          )
        //
        //}
        //dp[i][j]=Math.max(nums[i]+dp[i+2][j],nums[i]+dp[i+1][j-1],nums[j]+dp[i][j-2],nums[j]+dp[i+1][j-1])
        //dp[i][j]=Math.max(nums[i]+Math.max(dp[i+2][j],dp[i+1][j-1]),nums[j]+Math.max(dp[i][j-2],dp[i+1][j-1]))
        
        int[][] dp = new int[nums.length+5][nums.length+5];
        
        for(int i=0;i<nums.length;i++){
            dp[i][i]=nums[i];
        }
        
        for(int j=1;j<nums.length;j++){
            for(int i=j-1;i>=0;i--){
                int left = nums[i]+Math.min(i+2>j?0:dp[i+2][j],i+1>j-1?0:dp[i+1][j-1]);
                int right = nums[j]+Math.min(j-2<i?0:dp[i][j-2],i+1>j-1?0:dp[i+1][j-1]);
                dp[i][j]=Math.max(left,right);
            }
        }
        
        
        return dp[0][nums.length-1]*2>=Arrays.stream(nums).sum();
    }
}
