package dfs.lc366;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Map<Integer, List<Integer>> result = new HashMap<>();

    public List<List<Integer>> findLeaves(TreeNode root) {
        DFS(root);
        return new ArrayList<>(result.values());
    }

    private int DFS(TreeNode node) {
        if (node.left == null && node.right == null) {
            result.computeIfAbsent(0, x -> new ArrayList<>()).add(node.val);
            return 0;
        }
        //这儿需要一点思考
        int maxDepth = 0;
        if (node.left != null) {
            maxDepth = Math.max(maxDepth, DFS(node.left) + 1);
        }
        if (node.right != null) {
            maxDepth = Math.max(maxDepth, DFS(node.right) + 1);
        }
        result.computeIfAbsent(maxDepth, x -> new ArrayList<>()).add(node.val);
        return maxDepth;
    }
}
