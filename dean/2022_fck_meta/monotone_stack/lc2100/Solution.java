package monotone_stack.lc2100;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int[] asc = new int[security.length];
        int[] des = new int[security.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= security.length; i++) {
            //注意这儿最后这个特殊值
            int val = i == security.length ? Integer.MAX_VALUE : security[i];
            if (!stack.isEmpty() && security[stack.peek()] < val) {
                while (!stack.isEmpty()) {
                    des[stack.pop()] = stack.size();
                }
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = security.length - 1; i >= -1; i--) {
            //注意这儿最后这个特殊值
            int val = i == -1 ? Integer.MAX_VALUE : security[i];
            if (!stack.isEmpty() && security[stack.peek()] < val) {
                while (!stack.isEmpty()) {
                    asc[stack.pop()] = stack.size();
                }
            }
            stack.push(i);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < security.length; i++) {
            if (asc[i] >= time && des[i] >= time) {
                result.add(i);
            }
        }
        return result;
    }
}
