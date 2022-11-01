package dp.lc45;

import java.util.Arrays;

public class Solution {
    public int jump(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result,Integer.MAX_VALUE);

        result[0]=0;
        for(int i=0;i<nums.length;i++){
            for(int j=1;j<=nums[i];j++){
                if(i+j>nums.length-1){
                    continue;
                }
                result[i+j]=Math.min(result[i+j],result[i]+1);
            }
        }
        return result[nums.length-1];
    }
}