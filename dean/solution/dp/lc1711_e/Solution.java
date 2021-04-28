package dp.lc1711_e;

public class Solution {
    /**
     * @param A: the given array
     * @return: the minimum sum of a falling path
     */
    public int minFallingPathSum(int[][] A) {
        //dp[i][j]=A[i][j]+min(dp[i+1][j],dp[i+1][j-1],dp[i+1][j+1])
        if(A.length==1) return A[0][0];
        int lengthI = A.length;
        int lengthJ = A[0].length;
        int[][] dp = new int[2][lengthJ];

        for(int i=0;i<lengthJ;i++){
            dp[(lengthI-1)%2][i]=A[lengthI-1][i];
        }

        for(int i=lengthI-2;i>=0;i--){
            for(int j=0;j<lengthJ;j++){
                int pre = (i+1)%2;
                int cur = i%2;
                int minVal = dp[pre][j];
                if(j+1<lengthJ){
                    minVal=Math.min(minVal,dp[pre][j+1]);
                }
                if(j-1>=0){
                    minVal=Math.min(minVal,dp[pre][j-1]);
                }
                dp[cur][j]=A[i][j]+minVal;
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=0;i<lengthJ;i++){
            min = Math.min(min,dp[0][i]);
        }

        return min;
    }
}
