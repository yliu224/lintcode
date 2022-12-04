package dfs.lc494;

public class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return DFS(nums, 0, nums[0], target) + DFS(nums, 0, -nums[0], target);
    }

    private int DFS(int[] nums, int index, int sum, int target) {
        int count = 0;
        if (index >= nums.length - 1) {
            return sum == target ? 1 : 0;
        }
        count += DFS(nums, index + 1, sum + nums[index + 1], target);
        count += DFS(nums, index + 1, sum - nums[index + 1], target);
        return count;
    }
}
