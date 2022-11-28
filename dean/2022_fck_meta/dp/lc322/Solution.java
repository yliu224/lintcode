package dp.lc322;

import java.util.Arrays;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] ans = new int[amount+1];
        Arrays.fill(ans,Integer.MAX_VALUE);
        ans[0]=0;
        for(int c:coins){
            for(int i=0;i<=amount;i++){
                if(i-c>=0 && ans[i-c]!=Integer.MAX_VALUE){
                    ans[i]=Math.min(ans[i-c]+1,ans[i]);
                }
            }
        }
        if(ans[amount]==Integer.MAX_VALUE){
            return -1;
        }
        return ans[amount];
    }
}
