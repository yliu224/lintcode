package monotone_stack.lc503;

import java.util.Arrays;
import java.util.Stack;

public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        int len = nums.length;
        for (int i = 0; i < len * 2; i++) {
            int val = nums[i % len];
            while (!stack.isEmpty() && nums[stack.peek() % len] < val) {
                int index = stack.pop();
                result[index] = val;
            }
            stack.push(i % len);
        }

        return result;
    }
}
