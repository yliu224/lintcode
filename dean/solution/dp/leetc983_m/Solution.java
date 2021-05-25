package dp.leetc983_m;

import java.util.*;

public class Solution {
    //注意TreeSet的使用
    private TreeSet<Integer> sortedDays = new TreeSet<>();

    public int mincostTickets(int[] days, int[] costs) {
        //dp[i]means min cost for day i
        int[] dp = new int[400];
        for(int i=0;i<days.length;i++){
            sortedDays.add(days[i]);
        }
        
        for(int i=0;i<days.length;i++){
            int oneDayAgo = getNDaysAgo(days[i],1);
            int sevenDaysAgo = getNDaysAgo(days[i],7);
            int thirtyDaysAgo = getNDaysAgo(days[i],30);

            dp[days[i]]=dp[oneDayAgo]+costs[0];
            dp[days[i]] = Math.min(dp[days[i]],dp[sevenDaysAgo]+costs[1]);
            dp[days[i]] = Math.min(dp[days[i]],dp[thirtyDaysAgo]+costs[2]);
        }        
        return dp[days[days.length-1]];
    }

    private int getNDaysAgo(int i,int n) {
        try{
            return this.sortedDays.lower(i-n+1);
        } catch(Exception e){
            return 0;
        }
    }
}
