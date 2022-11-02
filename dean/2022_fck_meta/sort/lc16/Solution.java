package sort.lc16;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int result = 0;
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                int thirdNum = findNum(i,j,nums,target);
                int diff = Math.abs((nums[i]+nums[j]+nums[thirdNum])-target);
                if(min>diff){
                    min = diff;
                    result = nums[i]+nums[j]+nums[thirdNum];
                }
            }
        }
        return result;
    }

    private int findNum(int i,int j,int[] nums,int target){
        int newTarget=target-nums[i]-nums[j];
        int l=0,r=nums.length-1;
        while(l+1<r){
            int mid = (l+r)/2;
            if(newTarget<nums[mid]){
                r = mid;
            } else{
                l = mid;
            }
        }
        while(l==i || l==j){
            if(l<=0){
                break;
            }
            l--;
        }
        while(r==i || r==j){
            if(r>=nums.length-1){
                break;
            }
            r++;
        }
        if(l==i || l==j){
            return r;
        }
        if(r==i || r==j){
            return l;
        }

        return Math.abs(nums[l]-newTarget)>Math.abs(nums[r]-newTarget)?r:l;
    }
}
