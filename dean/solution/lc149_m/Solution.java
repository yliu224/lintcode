package dean.solution.lc149_m;

public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        int maxValue = Integer.MIN_VALUE;
        int minPrice = Integer.MAX_VALUE;

        for(int i=0;i<prices.length;i++){
            minPrice = Math.min(prices[i],minPrice);
            maxValue = Math.max(prices[i]-minPrice,maxValue);
        }

        return maxValue;
    }
}
