package stack.lc735;

import java.util.Stack;

public class Solution {
    //很多情况要考虑，先写一些test case
    //[-2,-2,1,-2]
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int a : asteroids) {
            if (a > 0) {
                stack.push(a);
            } else {
                if (!stack.isEmpty() && stack.peek() < 0) {
                    stack.push(a);
                    continue;
                }
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() + a < 0) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() > 0 && stack.peek() + a == 0) {
                    stack.pop();
                    continue;
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(a);
                }
            }
        }
        return stack.stream().mapToInt(x -> x).toArray();
    }
}
