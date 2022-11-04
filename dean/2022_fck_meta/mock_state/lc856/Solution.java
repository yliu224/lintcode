package mock_state.lc856;

import java.util.Stack;

public class Solution {
    public int scoreOfParentheses(String s) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(-1);
            }
            if (c == ')') {
                int val = 0;
                if (stack.peek() == -1) {
                    stack.pop();
                    val = 1;
                } else {
                    int tmpVal = stack.pop();
                    stack.pop();
                    val = tmpVal * 2;
                }
                if (stack.isEmpty() || stack.peek() == -1) {
                    stack.push(val);
                } else {
                    stack.push(stack.pop() + val);
                }
            }
        }
        return stack.peek();
    }
}
