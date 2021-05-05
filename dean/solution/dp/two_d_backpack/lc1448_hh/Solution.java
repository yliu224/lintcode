package dp.two_d_backpack.lc1448_hh;

public class Solution{
    private int MOD=1000000007;
    //dp[i][j]=dp[i-package[j]][j-1]
    //这道题实质是一个背包问题
    // i:当profit>i,    j:当cost==j,        k: card index
    //                  这里一定是 cost==j
    //dp[i][j][k]=dp[i][j][k-1]+dp[i-card[k].profit][j-card[k].cost][k-1]
    public int numOfPlan(int n, int totalProfit, int totalCost, int[] a, int[] b) {
        int[][][] dp = new int[totalProfit+5][totalCost+5][n+5];

        //注意这个[0][0][0]的初始值1
        dp[0][0][0]=1;
        for(int i=0;i<=Math.min(totalProfit+1,a[0]);i++){//这儿是小于等于，
           dp[i][b[0]][0]+=1;//注意这儿是加好
        }

        for(int k=1;k<n;k++){
            for(int i=0;i<totalProfit+5;i++){
                for(int j=0;j<totalCost+5;j++){
                    dp[i][j][k]=dp[i][j][k-1];
                    if(j-b[k]>=0){
                        dp[i][j][k]+=dp[Math.max(0,i-a[k])][j-b[k]][k-1];
                    }
                    dp[i][j][k]%=MOD;
                }
            }
        }

        int res=0;
        for(int i=0;i<totalCost;i++){
            res+=dp[totalProfit+1][i][n-1];//注意取Profit+1
            res%=MOD;
        }
        return res;
    }
}