package dfs.lc94_m;

import datastructure.TreeNode;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer
     */
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root==null) return 0;

        DFS(root);
        return max;
    }

    private int DFS(TreeNode root){
        if(root.left==null && root.right==null){
            max=Math.max(root.val,max);
            return root.val;
        }

        int maxLeft = 0;
        int maxRight = 0;
        if(root.left!=null){
            maxLeft = DFS(root.left);
        }

        if(root.right!=null){
            maxRight = DFS(root.right);
        }

        int localMax = root.val;
        if(maxLeft>0){
            localMax = localMax+maxLeft;
        }
        if(maxRight>0){
            localMax = localMax+maxRight;
        }
        max = Math.max(localMax,max);
        return Math.max(root.val+maxLeft,root.val+maxRight);
    }
}
