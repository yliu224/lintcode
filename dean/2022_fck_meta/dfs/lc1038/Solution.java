package dfs.lc1038;

import datastructure.TreeNode;

public class Solution {
    public TreeNode bstToGst(TreeNode root) {
        DFS(root, 0);
        return root;
    }

    private int DFS(TreeNode node, int curSum) {
        if (node.left == null && node.right == null) {
            if (curSum == 0) {
                return node.val;
            } else {
                node.val += curSum;
                return node.val;
            }
        }

        int sum = curSum;
        if (node.right != null) {
            //注意这儿是等于，把DFS定义好
            sum = DFS(node.right, curSum);
        }
        sum += node.val;
        node.val = sum;
        if (node.left != null) {
            //注意这儿是等于，把DFS定义好
            sum = DFS(node.left, sum);
        }
        return sum;
    }
}
