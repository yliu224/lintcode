package dp.lc515_e;

public class Solution {
    /**
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    //dp[i][0]=cost[i][0]+Math.min(dp[i-1][1],dp[i-1][2])
    //dp[i][1]=cost[i][1]+Math.min(dp[i-1][0],dp[i-1][2])
    //dp[i][2]=cost[i][2]+Math.min(dp[i-1][0],dp[i-1][1])
    public int minCost(int[][] costs) {
        if(costs.length==0) return 0;
        int[][] dp = new int[2][3];
        dp[0][0]=costs[0][0];
        dp[0][1]=costs[0][1];
        dp[0][2]=costs[0][2];

        for(int i=1;i<costs.length;i++){
            int cur = i%2;
            int pre = (i+1)%2;

            dp[cur][1]=costs[i][1]+Math.min(dp[pre][0],dp[pre][2]);
            dp[cur][2]=costs[i][2]+Math.min(dp[pre][1],dp[pre][0]);
            dp[cur][0]=costs[i][0]+Math.min(dp[pre][1],dp[pre][2]);
        }

        int lastHouse = (costs.length+1)%2;
        return Math.min(dp[lastHouse][1],Math.min(dp[lastHouse][0],dp[lastHouse][2]));
    }
}
