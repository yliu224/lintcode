package dfs.lc46;

import java.util.*;

public class Solution {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        DFS(new LinkedList<>(), nums);
        return result;
    }

    void DFS(LinkedList<Integer> path, int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                path.addLast(nums[i]);
                nums[i] = Integer.MAX_VALUE;
                DFS(path, nums);
                nums[i] = path.removeLast();
            }
        }
    }
}
