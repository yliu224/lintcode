package binary_search.leet34_e;

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0)
            return new int[] { -1, -1 };
        int minLeft, minRight;
        int left = 0, right = nums.length - 1;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        minLeft = nums[left] == target ? left : nums[right] == target ? right : -1;

        left = 0;
        right = nums.length - 1;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        minRight = nums[right] == target ? right : nums[left] == target ? left : -1;

        return new int[] { minLeft, minRight };
    }
}
