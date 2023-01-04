package two_pointer.lc324;

import java.util.Arrays;

public class Solution {
    public void wiggleSort(int[] nums) {
        int[] copied = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copied);

        int mid = (nums.length - 1) / 2;
        int minIndex = 0;
        for (int i = mid; i >= 0; i--) {
            nums[minIndex] = copied[i];
            minIndex += 2;
        }

        int maxIndex = 1;
        for (int i = nums.length - 1; i >= mid + 1; i--) {
            nums[maxIndex] = copied[i];
            maxIndex += 2;
        }
    }
}
