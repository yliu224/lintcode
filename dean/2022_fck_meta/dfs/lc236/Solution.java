package dfs.lc236;

import datastructure.TreeNode;

public class Solution {
    private boolean isFound=false;
    private TreeNode ans=null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        DFS(root,p,q);
        return ans;
    }

    private int DFS(TreeNode node,TreeNode t1,TreeNode t2){
        if(isFound){
            return 0;
        }
        int count = 0;
        if(node==t1 || node==t2){
            count++;
        }
        if(node.left!=null){
            count+=DFS(node.left,t1,t2);
        }
        if(node.right!=null){
            count+=DFS(node.right,t1,t2);
        }
        if(count==2){
            isFound=true;
            ans = node;
            //注意这儿需要return
            return 0;
        }
        return count;
    }
}
