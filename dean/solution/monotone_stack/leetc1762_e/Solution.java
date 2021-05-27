package monotone_stack.leetc1762_e;

import java.util.Stack;

class Solution {
    public int[] findBuildings(int[] heights) {
        Stack<Integer> buildings = new Stack<>();

        buildings.push(0);
        for (int i = 1; i < heights.length; i++) {
            while (!buildings.isEmpty() && heights[i] >= heights[buildings.peek()]) {
                buildings.pop();
            }
            buildings.push(i);
        }

        int[] answer = buildings.stream().mapToInt(t -> t).toArray();

        return answer;
    }
}