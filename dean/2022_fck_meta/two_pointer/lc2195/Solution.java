package two_pointer.lc2195;

import java.util.Arrays;

public class Solution {
    public long minimalKSum(int[] nums, int k) {
        //注意题中没说Sort就要自己去做Sort
        Arrays.sort(nums);
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int diff = getDiff(nums, i);
            if (diff == 0) continue;
            int start = getStart(nums, i);
            int count = Math.min(k, diff);
            sum += getAns(start, count);
            k -= count;
            if (k == 0) {
                return sum;
            }
        }
        if (k != 0) {
            sum += getAns(nums[nums.length - 1] + 1, k);
        }
        return sum;
    }

    private int getDiff(int[] nums, int i) {
        int l = 0;
        if (i != 0) {
            //可能会有重复选项
            if (nums[i - 1] == nums[i]) return 0;
            l = nums[i - 1];
        }
        int r = nums[i];
        return r - l - 1;
    }

    private int getStart(int[] nums, int i) {
        int start = 0;
        if (i != 0) {
            start = nums[i - 1];
        }
        return start + 1;
    }

    private long getAns(long start, long count) {
        return ((start + (start + count - 1)) * count) / 2;
    }
}
