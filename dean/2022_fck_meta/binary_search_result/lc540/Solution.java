package binary_search_result.lc540;

public class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (leftIsOdd(mid, nums)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return findSingle(l, r, nums);
    }

    private boolean leftIsOdd(int mid, int[] nums) {
        //这个地方是绝对值，主要看mid在哪边
        if (mid + 1 < nums.length && nums[mid + 1] == nums[mid]) {
            return mid % 2 == 1;
        }
        if (mid - 1 >= 0 && nums[mid - 1] == nums[mid]) {
            //这个地方是绝对值，主要看mid在哪边
            return (mid + 1) % 2 == 1;
        }
        //这个地方return true或者false没有关系
        return false;
    }

    private int findSingle(int l, int r, int[] nums) {
        if (l - 1 < 0 || nums[l - 1] != nums[l]) {
            return nums[l];
        }
        return nums[r];
    }
}
