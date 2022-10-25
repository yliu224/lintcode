package tree.lc530;

import datastructure.TreeNode;

public class Solution {
    private int min = Integer.MAX_VALUE;
    private TreeNode pre;

    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return min;
    }

    private void inOrder(TreeNode n) {
        if (n == null) {
            return;
        }
        inOrder(n.left);
        if (pre != null) {
            min = Math.min(Math.abs(pre.val - n.val), min);
        }
        pre = n;
        inOrder(n.right);
    }
}