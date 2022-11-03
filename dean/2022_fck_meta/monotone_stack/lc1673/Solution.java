package monotone_stack.lc1673;

import java.util.Stack;

public class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            //注意这个状态的变化
            while (!stack.isEmpty() && stack.peek() > nums[i] && stack.size() - 1 + nums.length - i >= k) {
                stack.pop();
            }
            stack.push(nums[i]);
        }

        int[] result = new int[k];
        while (stack.size() > k) {
            stack.pop();
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }
}
