package dp.lc213;

public class Solution {
    //分两种情况讨论
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = -1;
        int[][] pickFirst = new int[nums.length][2];
        int[][] nonPickFirst = new int[nums.length][2];

        pickFirst[1][0] = 0;
        pickFirst[1][1] = nums[0];
        max = nums[0];

        for (int i = 2; i < nums.length - 1; i++) {
            pickFirst[i][0] = pickFirst[i - 1][1] + nums[i];
            pickFirst[i][1] = Math.max(pickFirst[i - 1][0], pickFirst[i - 1][1]);
            max = Math.max(max, Math.max(pickFirst[i][0], pickFirst[i][1]));
        }
        nonPickFirst[0][0] = 0;
        nonPickFirst[0][1] = 0;
        for (int i = 1; i < nums.length; i++) {
            nonPickFirst[i][0] = nonPickFirst[i - 1][1] + nums[i];
            nonPickFirst[i][1] = Math.max(nonPickFirst[i - 1][0], nonPickFirst[i - 1][1]);
            max = Math.max(max, Math.max(nonPickFirst[i][0], nonPickFirst[i][1]));
        }
        return max;
    }
}