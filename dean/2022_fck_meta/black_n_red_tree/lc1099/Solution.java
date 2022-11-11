package black_n_red_tree.lc1099;

import java.util.TreeSet;

public class Solution {
    private TreeSet<Integer> ts = new TreeSet<>();
    public int twoSumLessThanK(int[] nums, int k) {
        int max = -1;
        if(nums.length<1){
            return max;
        }
        ts.add(nums[0]);
        for(int i=1;i<nums.length;i++){
            int target = k-nums[i];
            Integer maxNumber = ts.lower(target);
            if(maxNumber!=null){
                max = Math.max(max,maxNumber+nums[i]);
            }
            ts.add(nums[i]);
        }
        return max;
    }
}
