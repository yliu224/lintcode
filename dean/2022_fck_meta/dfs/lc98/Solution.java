package dfs.lc98;

import datastructure.TreeNode;

public class Solution {
    private boolean isValid = true;

    public boolean isValidBST(TreeNode root) {
        DFS(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return isValid;
    }

    private void DFS(TreeNode node, long left, long right) {
        if (!isValid) {
            return;
        }
        if (node.val <= left || node.val >= right) {
            isValid = false;
            return;
        }
        if (node.left != null) {
            DFS(node.left, left, node.val);
        }
        if (node.right != null) {
            DFS(node.right, node.val, right);
        }
    }
}
