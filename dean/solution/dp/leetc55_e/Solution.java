package dp.leetc55_e;

import java.util.*;

public class Solution {
    public boolean canJump(int[] nums) {
        boolean[] step = new boolean[nums.length];
        Arrays.fill(step, false);

        step[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (step[nums.length - 1]) {
                return true;
            }

            if (step[i] == false) {
                return false;
            }
            for (int j = 0; j < Math.min(nums[i], nums.length); j++) {
                if (i + 1 + j < nums.length) {
                    step[i + 1 + j] = true;
                }
            }
        }

        return step[nums.length - 1];
    }
}
