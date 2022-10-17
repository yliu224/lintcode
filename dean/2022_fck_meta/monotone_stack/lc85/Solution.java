package monotone_stack.lc85;

import java.util.Stack;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        int[] graph = new int[matrix[0].length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    graph[j] += 1;
                } else {
                    graph[j] = 0;
                }
            }
            max = Math.max(max, getMax(graph));
        }
        return max;
    }

    private int getMax(int[] graph) {
        Stack<Integer> stack = new Stack<>();
        int[] asc = new int[graph.length + 5];

        for (int i = 0; i <= graph.length; i++) {
            int value = i == graph.length ? -1 : graph[i];
            while (!stack.isEmpty() && value < graph[stack.peek()]) {
                asc[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack.clear();
        int max = Integer.MIN_VALUE;
        for (int i = graph.length - 1; i >= -1; i--) {
            int value = i == -1 ? -1 : graph[i];
            while (!stack.isEmpty() && value < graph[stack.peek()]) {
                int top = stack.pop();
                int l = i + 1;
                int r = asc[top];
                int h = graph[top];
                max = Math.max(max, (r - l) * h);
            }
            stack.push(i);
        }
        return max;

    }
}
