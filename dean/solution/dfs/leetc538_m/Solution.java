package dfs.leetc538_m;

import datastructure.TreeNode;

public class Solution {
        private int sum = 0;
        public TreeNode convertBST(TreeNode root) {
            if(root==null) return root;
            DFS(root,0);
            
            return root;
        }
        
        public int DFS(TreeNode node,int sum){
            if(node.left==null && node.right==null){
                node.val = sum+node.val;
                return node.val;
            }
            
            if(node.right!=null){
                int rightSum = DFS(node.right,sum);
                node.val=rightSum+node.val;
            } else{
                node.val=node.val+sum;
            }
            
            if(node.left!=null){
                return DFS(node.left,node.val);    
            } else{
                return node.val;
            }
            
        }
    }
}
