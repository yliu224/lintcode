package bfs.backpack.lc440_m;

//多重背包经典
public class Solution {
    /**
     * @param A: an integer array
     * @param V: an integer array
     * @param m: An integer
     * @return: an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        int[] dp = new int[m+1];
        dp[0]=0;

        for(int i=0;i<A.length;i++){
            for(int j=0;j<=m;j++){
                if(j-A[i]>=0){
                    dp[j]=Math.max(dp[j],dp[j-A[i]]+V[i]);
                }
            }
        }
        return dp[m];
    }
}
