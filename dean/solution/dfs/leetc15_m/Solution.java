package dfs.leetc15_m;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    //其实是一个stack的题
    private Set<String> visited;
    private List<List<Integer>> result;
    private Map<Integer, Integer> count;

    public List<List<Integer>> threeSum(int[] nums) {
        visited = new HashSet<>();
        result = new LinkedList<>();
        count = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int c = count.getOrDefault(nums[i], 0) + 1;
            count.put(nums[i], c);
        }
        DFS(1, 0, new Stack<>());
        return result;
    }

    public void DFS(int step, int leftTarget, Stack<Integer> stack) {
        if (step == 3) {// n sum就把这个改成 n
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
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {//这儿可以只用for map就好了
            if (e.getValue() >= 1) {
                count.put(e.getKey(), count.get(e.getKey()) - 1);
                stack.push(e.getKey());
                DFS(step + 1, leftTarget - e.getKey(), stack);
                stack.pop();
                count.put(e.getKey(), count.get(e.getKey()) + 1);
            }
        }
    }
}
