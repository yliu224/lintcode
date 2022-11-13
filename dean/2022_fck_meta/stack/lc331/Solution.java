package stack.lc331;

import java.util.Stack;

public class Solution {
    public boolean isValidSerialization(String preorder) {
        Stack<int[]> stack = new Stack<>();
        String[] str = preorder.split(",");
        //注意思考 special case
        if (str.length == 1 && str[0].equals("#")) {
            return true;
        }
        if (str[0].equals("#")) {
            return false;
        }
        stack.push(new int[]{Integer.parseInt(str[0]), 0});
        for (int i = 1; i < str.length; i++) {
            String s = str[i];
            if (stack.isEmpty()) {
                return false;
            }
            stack.peek()[1]++;
            if (s.equals("#")) {
                while (!stack.isEmpty() && stack.peek()[1] == 2) {
                    stack.pop();
                }
            } else {
                stack.push(new int[]{Integer.parseInt(s), 0});
            }
        }
        return stack.isEmpty();
    }
}
