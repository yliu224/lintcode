package mock_state.leetc268_e;

public class Solution {
    public int missingNumber(int[] nums) {
        long result = (nums.length * (nums.length + 1)) / 2;

        for (int i = 0; i < nums.length; i++) {
            result = result - nums[i];
        }

        return (int) result;
    }
}
