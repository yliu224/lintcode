package dfs.leetc18_h;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    //DFS注意用全局变量来存数据，这样快！！！
    private Set<String> visited;
    private List<List<Integer>> result;
    private Map<Integer, Integer> count;

    public List<List<Integer>> fourSum(int[] nums, int target) {
        visited = new HashSet<>();
        result = new LinkedList<>();
        count = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int c = count.getOrDefault(nums[i], 0) + 1;
            count.put(nums[i], c);
        }
        DFS(nums, 0, 1, target, new Stack<>());
        return result;
    }

    public void DFS(int[] nums, int index, int step, int leftTarget, Stack<Integer> stack) {
        if (step == 4) {//n sum就把这个改成 n
            if (count.getOrDefault(leftTarget, 0) > 0) {
                stack.push(leftTarget);
                List<Integer> s = stack.stream().sorted().collect(Collectors.toList());
                if (!visited.contains(s.toString())) {
                    visited.add(s.toString());
                    result.add(s);
                }
                stack.pop();
            }
            return;
        }
        for (int i = index; i < nums.length; i++) {
            count.put(nums[i], count.get(nums[i]) - 1);
            stack.push(nums[i]);
            DFS(nums, i + 1, step + 1, leftTarget - nums[i], stack);
            stack.pop();
            count.put(nums[i], count.get(nums[i]) + 1);
        }
    }
}
