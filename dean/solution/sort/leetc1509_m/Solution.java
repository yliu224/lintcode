package sort.leetc1509_m;

import java.util.*;

public class Solution {
    public int minDifference(int[] nums) {
        if (nums.length <= 4)
            return 0;
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;

        min = Math.min(min, Math.abs(nums[0] - nums[nums.length - 4]));
        min = Math.min(min, Math.abs(nums[1] - nums[nums.length - 3]));
        min = Math.min(min, Math.abs(nums[2] - nums[nums.length - 2]));
        min = Math.min(min, Math.abs(nums[3] - nums[nums.length - 1]));

        return min;
    }
}
