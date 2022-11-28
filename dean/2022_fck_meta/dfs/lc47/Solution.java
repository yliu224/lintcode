package dfs.lc47;

import java.util.*;

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        return DFS(new LinkedList<>(), new HashSet<>(), nums);
    }

    List<List<Integer>> DFS(LinkedList<Integer> path, HashSet<String> visited, int[] nums) {
        if (path.size() == nums.length && !visited.contains(path.toString())) {
            visited.add(path.toString());
            return List.of(new ArrayList<>(path));
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                path.addLast(nums[i]);
                nums[i] = Integer.MAX_VALUE;
                result.addAll(DFS(path, visited, nums));
                nums[i] = path.removeLast();
            }
        }
        return result;
    }
}