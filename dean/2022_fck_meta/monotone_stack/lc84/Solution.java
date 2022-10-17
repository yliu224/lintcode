package monotone_stack.lc84;

import java.util.Stack;

public class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] asc = new int[heights.length + 5];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= heights.length; i++) {
            int value = i == heights.length ? -1 : heights[i];
            while (!stack.isEmpty() && value < heights[stack.peek()]) {
                asc[stack.pop()] = i;
            }
            stack.push(i);
        }

        int max = Integer.MIN_VALUE;
        stack.clear();
        for (int i = heights.length - 1; i >= -1; i--) {
            int value = i == -1 ? -1 : heights[i];
            while (!stack.isEmpty() && value < heights[stack.peek()]) {
                int cur = stack.pop();
                int l = i + 1;
                int r = asc[cur];
                int h = heights[cur];

                max = Math.max(max, (r - l) * h);
            }
            stack.push(i);
        }
        return max;
    }
}