package two_pointer.lc163;

import java.util.*;

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int[] newNums = new int[nums.length + 2];
        newNums[0] = lower - 1;
        newNums[newNums.length - 1] = upper + 1;
        for (int i = 0; i < nums.length; i++) {
            newNums[i + 1] = nums[i];
        }
        int l = 0, r = 1;
        while (r < newNums.length) {
            if (newNums[l] + 1 == newNums[r] - 1) {
                result.add(String.valueOf(newNums[l] + 1));
            } else if (newNums[l] + 1 < newNums[r] - 1) {
                result.add(String.format("%d->%d", newNums[l] + 1, newNums[r] - 1));
            }
            l++;
            r++;
        }
        return result;
    }
}
