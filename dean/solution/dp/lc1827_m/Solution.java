package dp.lc1827_m;

public class Solution {
    /**
     * @param steps: steps you can move
     * @param arrLen: the length of the array
     * @return: Number of Ways to Stay in the Same Place After Some Steps
     */
    private int MOD=(int)Math.pow(10,9)+7;
    public int numWays(int steps, int arrLen) {
        //dp[i][j] 走第i步到第j格的方法数
        //dp[i][j]=dp[i-1][j-1]+dp[i-1][j]+dp[i-1][j+1]
        //3步，5个格子
        //Step 1: 1      1       0       0       0
        //Step 2: 1+1    1+1     1       0       0
        //Step 3: 2+2    2+2+1   2+1     1       0      
        int[][] step=new int[2][arrLen];

        step[0][0]=1;
        step[0][1]=1;

        for(int i=2;i<=steps;i++){
            for(int j=0;j<Math.min(i+1,arrLen);j++){//优化for的次数
                step[(i+1)%2][j]=0;
                if(j-1>=0){
                    step[(i+1)%2][j]+=step[i%2][j-1];
                    step[(i+1)%2][j]%=MOD;//注意每一步都要取mod
                }
                step[(i+1)%2][j]+=step[i%2][j];
                step[(i+1)%2][j]%=MOD;
                if(j+1<=arrLen-1){
                    step[(i+1)%2][j]+=step[i%2][j+1];
                    step[(i+1)%2][j]%=MOD;
                }
                step[(i+1)%2][j]=step[(i+1)%2][j]%MOD;
            }
        }

        if(steps==0) return 0;
        if(steps==1) return step[0][0];
        return step[(steps+1)%2][0];
    }
}
