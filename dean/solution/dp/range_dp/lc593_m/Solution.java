package dp.range_dp.lc593_m;

//处理环的问题可以长度直接 lenX2
public class Solution {
    /**
     * @param A: An integer array
     * @return: An integer
     */
    //dp[i][j]=dp[i][k]+dp[k+1][j]+sum[i][j](i<k<j)
    //dp[i][j]表示i到j合并所花费的最小值
    //sum[i][j]表示i到j的和
    //k是i<k<j每个都for一遍
    private int[] prefixSum;
    public int stoneGame2(int[] A) {
        if(A==null || A.length==0) return 0;

        int len = A.length;
        buildPrefixSum(A);
        int[][] dp=new int[2*len][2*len];//x2
        for(int range=2;range<=len;range++){
            for(int i=0;i+(range-1)<len*2;i++){//x2
                int j=i+(range-1);
                int min=Integer.MAX_VALUE;
                for(int k=i;k<j;k++){
                    min=Math.min(min,dp[i][k]+dp[k+1][j]);
                }
                dp[i][j]=min+getSum(i,i+range);//注意这儿是i+range 
            }
        }

        int min=Integer.MAX_VALUE;
        for(int i=0;i<len;i++){
            min = Math.min(min,dp[i][i+len-1]);
        }
        return min;
    }

    //注意这个build这儿要变
    private void buildPrefixSum(int[] A){
        prefixSum = new int[(2*A.length)+1];
        for(int i=0;i<A.length*2;i++){
            prefixSum[i+1]=prefixSum[i]+A[i%A.length];
        }
    }

    private int getSum(int i,int j){
        return prefixSum[j]-prefixSum[i];
    }
}
