package dp.leetc124_m;

import datastructure.TreeNode;

class Solution {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        DFS(root);
        return max;
    }

    private int DFS(TreeNode node) {
        if (node.left == null && node.right == null) {
            max = Math.max(max, node.val);
            return node.val;
        }

        int leftSum = 0;
        if (node.left != null) {
            leftSum = DFS(node.left);
        }

        int rightSum = 0;
        if (node.right != null) {
            rightSum = DFS(node.right);
        }

        // 注意这下面一堆判断条件
        int maxSum = Math.max(leftSum + node.val, rightSum + node.val);
        maxSum = Math.max(node.val, maxSum);
        max = Math.max(maxSum, max);
        max = Math.max(leftSum + node.val + rightSum, max);
        return maxSum;
    }
}
