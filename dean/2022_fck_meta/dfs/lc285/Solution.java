package dfs.lc285;

import datastructure.TreeNode;

public class Solution {
    private TreeNode min = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        FindParent(root, p, null);
        FindChildren(p.right);
        return min;
    }

    void FindChildren(TreeNode node) {
        if (node == null) {
            return;
        }
        if (min == null || node.val < min.val) {
            min = node;
        }
        FindChildren(node.left);
    }

    void FindParent(TreeNode node, TreeNode target, TreeNode parent) {
        if (min != null) {
            return;
        }
        if (node == target) {
            min = parent;
            return;
        }
        if (node.left != null) {
            FindParent(node.left, target, node);
        }
        if (node.right != null) {
            FindParent(node.right, target, parent);
        }
    }
}
