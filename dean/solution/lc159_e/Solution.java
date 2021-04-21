package lc159_e;

public class Solution{
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums[0]<nums[nums.length-1]){
            return nums[0];
        }
        int left = 0,right = nums.length-1;
        while(left+1<right){
            int mid = (left+right)/2;

            if(nums[mid]>nums[left]){
                left = mid;
            } else{
                right = mid;
            }
        }

        return Math.min(nums[left],nums[right]);
    }
}