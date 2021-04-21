package two_pointer.lc151_h;

//隔板法，很重要
public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length<=1) return 0;
        int[] leftMaxProfit = maxLeftProfit(prices);
        int[] rightMaxProfit = maxRightProfit(prices);

        int maxProfit=leftMaxProfit[prices.length-1];

        for(int i=1;i<prices.length-2;i++){
            maxProfit=Math.max(maxProfit,leftMaxProfit[i]+rightMaxProfit[i+1]);
        }
        return maxProfit;
    }

    public int[] maxLeftProfit(int[] prices){
        int[] maxProfit = new int[prices.length];
        maxProfit[0]=0;
        int minPrice = prices[0];
        for(int i=1;i<prices.length;i++){
            maxProfit[i]=Math.max(prices[i]-minPrice,maxProfit[i-1]);
            minPrice=Math.min(minPrice,prices[i]);
        }
        return maxProfit;
    }

    public int[] maxRightProfit(int[] prices){
        int length = prices.length-1;
        int[] maxProfit = new int[length+1];
        int maxPrice = prices[length];
        maxProfit[length]=0;
        for(int i=length-1;i>=0;i--){
            maxProfit[i]=Math.max(maxProfit[i+1],maxPrice-prices[i]);
            maxPrice=Math.max(maxPrice,prices[i]);
        }
        return maxProfit;
    }
}
