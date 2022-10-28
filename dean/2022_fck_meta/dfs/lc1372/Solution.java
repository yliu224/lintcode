package dfs.lc1372;

import datastructure.TreeNode;

public class Solution {
    private int max;

    public int longestZigZag(TreeNode root) {
        if (root.left != null) {
            DFS(root.left, true, false, 1);
        }
        if (root.right != null) {
            DFS(root.right, false, true, 1);
        }
        return max;
    }

    private void DFS(TreeNode node, boolean l, boolean r, int count) {
        max = Math.max(max, count);
        if (node.left == null && node.right == null) {
            return;
        }
        if (node.left != null) {
            DFS(node.left, true, false, l ? 1 : count + 1);
        }
        if (node.right != null) {
            DFS(node.right, false, true, r ? 1 : count + 1);
        }
    }
}
