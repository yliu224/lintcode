package lc1849_e;

public class Solution {
    /**
     * @param customers: the number of customers
     * @param grumpy: the owner's temper every day
     * @param X: X days
     * @return: calc the max satisfied customers
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        // write your code here
        int sum=0;
        for(int i=0;i<customers.length;i++){
            if(i<X){
                sum+=customers[i];
            } else{
                sum+= grumpy[i]==1?0:customers[i];
            }
        }
        
        int left=0,right=X;
        int max = sum;
        while(right<customers.length){
            if(grumpy[left]==1){
                sum-=customers[left];
            }
            if(grumpy[right]==1){
                sum+=customers[right];
            }
            max=Math.max(max,sum);
            
            left++;
            right++;
        }
        
        return max;
    }
}
