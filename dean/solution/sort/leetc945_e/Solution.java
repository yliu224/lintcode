package sort.leetc945_e;

import java.util.*;

public class Solution {
    public int minIncrementForUnique(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        Set<Integer> visited = new HashSet<>();
        int step = 0;
        Arrays.sort(nums);

        visited.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (visited.contains(nums[i])) {
                step = step + (nums[i - 1] + 1 - nums[i]);
                nums[i] = nums[i - 1] + 1;
            }
            visited.add(nums[i]);
        }
        return step;
    }
}
