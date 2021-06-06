package bfs.leetc270_e;

import datastructure.TreeNode;

public class Solution {
    private int min;

    public int closestValue(TreeNode root, double target) {
        if (root.val == target) {
            return root.val;
        }

        min = root.val;
        TreeNode node = root.val > target ? root.left : root.right;

        while (node != null) {
            if (Math.abs(node.val * 1.0 - target) < Math.abs(min * 1.0 - target)) {
                min = node.val;
            }
            node = node.val > target ? node.left : node.right;
        }

        return min;
    }
}
