package dfs.leetc199_m;

import java.util.*;

import datastructure.TreeNode;

public class Solution {
    private List<Integer> result;

    public List<Integer> rightSideView(TreeNode root) {
        result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        DFS(root, 1);

        return result;
    }

    public void DFS(TreeNode node, int depth) {
        if (depth > result.size()) {
            result.add(node.val);
        }
        if (node.left == null && node.right == null) {
            return;
        }

        if (node.right != null) {
            DFS(node.right, depth + 1);
        }
        if (node.left != null) {
            DFS(node.left, depth + 1);
        }
    }
}
