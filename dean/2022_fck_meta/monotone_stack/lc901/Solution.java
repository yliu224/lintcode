package monotone_stack.lc901;

import java.util.Stack;

public class Solution {
    private Stack<int[]> stack;

    public Solution() {
        this.stack = new Stack<>();
    }

    public int next(int price) {
        //带状态的mono stack
        int ans = 0;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            ans += stack.pop()[1];
        }
        stack.push(new int[]{price, ans + 1});
        return ans + 1;
    }
}
