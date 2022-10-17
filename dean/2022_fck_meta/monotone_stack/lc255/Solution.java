package monotone_stack.lc255;

import java.util.Stack;

public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        int low = Integer.MIN_VALUE;
        for (int p : preorder) {
            //利用bineary search tree的性质
            if (p < low) {
                return false;
            }
            //Stack 里面已经保证了单调性
            while (!stack.isEmpty() && p > stack.peek()) {
                low = stack.pop();
            }
            stack.push(p);
        }
        return true;
    }
}
