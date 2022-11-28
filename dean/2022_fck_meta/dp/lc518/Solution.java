package dp.lc518;

public class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        //如果是2维dp
        //dp[i][j]表示第i个coin组成j块钱的组合数量
        //dp[i][j]=dp[i-1][j]+dp[i][j-coins[i]]
        //变成一维
        //dp[i]=dp[i]+dp[i-c]
        dp[0]=1;
        for(int c:coins){
            for(int i=0;i<=amount;i++){
                if(i-c>=0){
                    dp[i]+=dp[i-c];
                }
            }
        }
        return dp[amount];
    }
}
