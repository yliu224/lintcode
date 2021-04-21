package two_pointer.lc1879_m;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * @param nums: the input array
     * @param target: the target number
     * @return: return the target pair
     */
    public List<List<Integer>> twoSumVII(int[] nums, int target) {
        if(nums == null || nums.length==0) return Arrays.asList();
        List<List<Integer>> answers = new ArrayList<>();
        // write your code here
        for(int i=0;i<nums.length-1;i++){
            try{
                int answer = find_target(nums,i+1,nums.length-1,target-nums[i]);
                answers.add(Arrays.asList(i,answer));
            } catch(Exception e){
                continue;
            }
        }
        return answers;
    }

    private int find_target(int[] nums, int left, int right, int target){
        int start = left;
        int end = right;
        while(left+1<right){
            int mid = (left+right)/2;
            //注意target也需要abs
            if(Math.abs(nums[mid])<Math.abs(target)){
                left=mid;
            } else {
                right=mid;
            } 
        }
        //需要看下限
        if(left-1>=start && nums[left-1]==target){
            return left-1;
        }
        if(nums[left]==target){
            return left;
        }

        if(nums[right]==target){
            return right;
        }
        //需要看上限
        if(nums[right+1]<=end && nums[right+1]==target){
            return right+1;
        }

        throw new RuntimeException(String.format("target %d not found",target));
    }
}