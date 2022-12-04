package dfs.lc90;

import java.util.*;

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        return DFS(nums, 0, new HashSet<>(), new LinkedList<>());
    }

    List<List<Integer>> DFS(int[] nums, int index, Set<String> visited, LinkedList<Integer> path) {
        List<List<Integer>> result = new ArrayList<>();
        if (index == 0) {
            result.add(new ArrayList<>());
        }
        for (int i = index; i < nums.length; i++) {
            path.addLast(nums[i]);
            System.out.println(path);
            if (!visited.contains(path.toString())) {
                visited.add(path.toString());
                result.add(new ArrayList<>(path));
                result.addAll(DFS(nums, i + 1, visited, path));
            }
            path.removeLast();
        }
        return result;
    }
}
