package lc617_h;

public class Solution {
    public double maxAverage(int[] nums, int k) {
        double right=Double.MIN_VALUE, left=Double.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            right = Math.max(right,nums[i]);
            left = Math.min(left,nums[i]);
        }
        
        while(left+1e-5<right){
            double mid = (left+right)/2;

            if(isValid(mid,nums,k)){
                left = mid;
            } else{
                right = mid;
            }
        }

        if(isValid(left,nums,k)){
            return left;
        }
        if(isValid(right,nums,k)){
            return right;
        }
        return -1;
    }

    //求一大上升子序列
    //https://www.lintcode.com/problem/41/ 看下面
    private boolean isValid(double mid, int[] nums, int k) {
        double rightPrefixSum=0;
        double leftPrefixSum=0;
        double minLeftPrefixSum=0;

        for(int i=0;i<k;i++){
            rightPrefixSum +=nums[i]-mid;
        }

        //注意这儿是小于等于
        //不然数不到最后一个
        for(int i=k;i<=nums.length;i++){
            if(rightPrefixSum-minLeftPrefixSum>=0){
                return true;
            }

            if(i<nums.length){
                leftPrefixSum +=nums[i-k]-mid;
                rightPrefixSum +=nums[i]-mid;
                minLeftPrefixSum = Math.min(leftPrefixSum,minLeftPrefixSum);
            }
        }

        return false;
    }

    //AC LC 41
    public int maxSubArray(int[] nums) {
        // write your code here
        int minPrefixSum = 0;
        int currentPrefixSum = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            currentPrefixSum+=nums[i];
            max = Math.max(currentPrefixSum-minPrefixSum,max);
            minPrefixSum = Math.min(currentPrefixSum,minPrefixSum);
        }    

        return max;
    }
}
