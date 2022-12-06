package dfs.lc18;

import java.util.*;

public class Solution {
    Map<Integer, Integer> count = new HashMap<>();
    Set<String> visited = new HashSet<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        for (int n : nums) {
            count.put(n, count.computeIfAbsent(n, x -> 0) + 1);
        }
        return DFS(nums, 0, 0, target, new LinkedList<>());
    }

    List<List<Integer>> DFS(int[] nums, int sum, int index, int target, LinkedList<Integer> path) {
        List<List<Integer>> result = new ArrayList<>();
        if (path.size() == 3) {
            int left = target - sum;
            if (count.get(left) != null && count.get(left) != 0) {
                List<Integer> tmp = new ArrayList<>(path);
                tmp.add(left);
                tmp.sort((a, b) -> a - b);
                if (!visited.contains(tmp.toString())) {
                    visited.add(tmp.toString());
                    result.add(tmp);
                }
            }
            return result;
        }

        for (int i = index; i < nums.length; i++) {
            path.addLast(nums[i]);
            count.put(nums[i], count.get(nums[i]) - 1);
            result.addAll(DFS(nums, sum + nums[i], i + 1, target, path));
            count.put(nums[i], count.get(nums[i]) + 1);
            path.removeLast();
        }
        return result;
    }
}
