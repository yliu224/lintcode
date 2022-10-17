package monotone_stack.lc456;

import java.util.Stack;

public class Solution {
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            while (!stack.isEmpty() && value < nums[stack.peek()]) {
                stack.pop();
                if (!stack.isEmpty() && nums[stack.peek()] < value) {
                    return true;
                }
            }
            stack.push(i);
        }

        stack.clear();
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            int value = nums[i];
            while (!stack.empty() && value > nums[stack.peek()]) {
                max = Math.max(max, nums[stack.pop()]);
            }
            if (value < max && max != Integer.MIN_VALUE) {
                return true;
            }
            stack.push(i);
        }
        return false;
    }
}
