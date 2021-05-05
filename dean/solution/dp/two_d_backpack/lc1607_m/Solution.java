package dp.two_d_backpack.lc1607_m;

public class Solution {
    /**
     * @param G: The people in a gang.
     * @param P: A profitable scheme any subset of these crimes that generates at least P profit.
     * @param group: The i-th crime requires group[i] gang members to participate.
     * @param profit: The i-th crime generates a profit[i].
     * @return: Return how many schemes can be chosen.
     */
    //dp[i][j][k]=dp[i-group[k]][j-profit[k]][k-1]+dp[i][j][k-1]
    private int MOD=1000000007;
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        if(group.length==0) return 0;
        int[][][] dp = new int[G+5][P+5][group.length];

        dp[0][0][0]=1;

        for(int i=0;i<=Math.min(P+5,profit[0]);i++){//注意是小于等于
            if(group[0]<G+5){//注意这儿要判断
                dp[group[0]][i][0]=1;
            }
        }

        for(int k=1;k<group.length;k++){
            for(int i=0;i<G+5;i++){
                for(int j=0;j<P+5;j++){
                    dp[i][j][k]=dp[i][j][k-1];
                    if(i-group[k]>=0){//注意这儿
                        dp[i][j][k]=(dp[i][j][k]+dp[i-group[k]][Math.max(0,j-profit[k])][k-1])%MOD;
                    }
                }
            }
        }
        int result = 0;
        for(int i=0;i<=G;i++){
            result=(result+dp[i][P][group.length-1])%MOD;
        }

        return result;
    }
}
