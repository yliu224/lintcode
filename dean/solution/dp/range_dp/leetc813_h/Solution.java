package dp.range_dp.leetc813_h;

class Solution {
    //注意看数据范围，可以直接暴力dp
    public double largestSumOfAverages(int[] nums, int k) {
        //dp[i][j]表示有i个数，j个分组所能得到的最大sum
        //dp[i][j]=Max(dp[m][j-1]+avg(sum(nums[m...i])))
        //               {m:j-1->i}
        int[] prefixSum = new int[nums.length+1];
        double[][] dp = new double[nums.length+5][k+5];
        
        int sum = 0;
        prefixSum[0]=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            prefixSum[i+1]=sum;
            dp[i+1][1]=(1.0*sum)/(i+1);
        }
        
        for(int j=2;j<=k;j++){
            for(int i=0;i<nums.length;i++){
                for(int m=j-1;m<i+1;m++){
                    dp[i+1][j]=Math.max(dp[i+1][j],dp[m][j-1]+(prefixSum[i+1]-prefixSum[m])/(1.0*(i+1-m)));
                }
            }
        }
        return dp[nums.length][k];
    }
}
