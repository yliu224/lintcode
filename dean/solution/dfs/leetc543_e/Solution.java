package dfs.leetc543_e;

import datastructure.TreeNode;

class Solution {
    private int max=0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null) return 0;
        DFS(root);
        return max;
    }
    
    private int DFS(TreeNode node){
        if(node.left==null && node.right==null){
            return 0;
        }
        int left=-1,right=-1;
        if(node.left!=null){
            left = DFS(node.left);
        }
        if(node.right!=null){
            right = DFS(node.right);
        }
        
        max = Math.max(max,left+right+2);
        
        return Math.max(left+1,right+1);
    }
}
