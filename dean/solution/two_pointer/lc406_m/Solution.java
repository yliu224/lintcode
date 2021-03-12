package dean.solution.two_pointer.lc406_m;

//标准双指针模板
public class Solution {
    public int minimumSize(int[] nums, int s) {
        // write your code here
        if(nums.length==0) return -1;
        int minLen=Integer.MAX_VALUE;

        int j=1;
        int sum=nums[0];
        for(int i=0;i<nums.length;i++){
            while(j<nums.length && sum<s){
                sum+=nums[j];
                j++;
            }

            if(sum>=s){
                minLen = Math.min(j-i,minLen);
            }
            sum-=nums[i];
        }

        return minLen==Integer.MAX_VALUE?-1:minLen;
    }
}
