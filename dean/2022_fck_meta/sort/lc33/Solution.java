package sort.lc33;

public class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[l]) {
                if (target <= nums[mid] && target >= nums[l]) {
                    r = mid;
                } else {
                    l = mid;
                }
            } else {
                if (target >= nums[mid] && target <= nums[r]) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
        }
        if (nums[l] == target) {
            return l;
        }
        if (nums[r] == target) {
            return r;
        }
        return -1;
    }
}
