package dfs.lc2265;

import datastructure.TreeNode;

public class Solution {
    private int count = 0;

    public int averageOfSubtree(TreeNode root) {
        DFS(root);
        return count;
    }

    private int[] DFS(TreeNode node) {
        int[] stats = new int[]{node.val, 1};
        if (node.left == null && node.right == null) {
            count++;
            return stats;
        }
        if (node.left != null) {
            int[] lstats = DFS(node.left);
            stats[0] += lstats[0];
            stats[1] += lstats[1];
        }
        if (node.right != null) {
            int[] rstats = DFS(node.right);
            stats[0] += rstats[0];
            stats[1] += rstats[1];
        }
        if (node.val == stats[0] / stats[1]) {
            count++;
        }
        return stats;
    }
}
