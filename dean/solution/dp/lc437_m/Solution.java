package dp.lc437_m;

public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
     //dp[k][n] k个人抄前n本连续的书的最优解
     //dp[k][i]=Min(Max(dp[k-1][j],sum(pages[m])))
     //                        {j=1,j->i} {m=j,m->i}
    public int copyBooks(int[] pages, int k) {
        int[][] dp=new int[k+5][pages.length+5];

        int[] prefixSum = getPrefixSum(pages);

        for(int i=0;i<pages.length;i++){
            dp[1][i+1]=prefixSum[i+1]-prefixSum[0];
        }

        for(int person=2;person<=k;person++){
            for(int i=0;i<pages.length;i++){
                int minCost = Integer.MAX_VALUE;
                for(int j=0;j<i;j++){
                    int maxCost = Math.max(dp[person-1][j+1],prefixSum[i+1]-prefixSum[j+1]);
                    minCost = Math.min(minCost,maxCost);
                }
                if(i==0){
                    dp[person][i+1]=dp[person-1][i+1];
                } else{ 
                    dp[person][i+1]=minCost;
                }
            }
        }

        return dp[k][pages.length];
    }

    private int[] getPrefixSum(int[] pages){
        int[] prefixSum = new int[pages.length+1];
        prefixSum[0]=0;
        for(int i=0;i<pages.length;i++){
            prefixSum[i+1]=prefixSum[i]+pages[i];
        }
        return prefixSum;
    }
}