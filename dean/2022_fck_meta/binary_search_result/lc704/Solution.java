package binary_search_result.lc704;

public class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        //记住，这儿是模版
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                r = mid;
            } else {
                l = mid;
            }
        }
        if (target == nums[l]) {
            return l;
        }
        if (target == nums[r]) {
            return r;
        }
        return -1;
    }
}
