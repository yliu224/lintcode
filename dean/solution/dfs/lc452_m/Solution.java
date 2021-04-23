package dfs.lc452_m;
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
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        if(root==null) return;
        flatDFS(root);
    }

    public TreeNode flatDFS(TreeNode root){
        if(root.left==null && root.right==null){
            return root;
        }

        if(root.left==null){
            return flatDFS(root.right);//注意这儿要返回的东西
        }

        if(root.right==null){
            root.right=root.left;
            root.left=null;
            return flatDFS(root.right);//注意这儿要返回的东西
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right=left;
        TreeNode lastNode = flatDFS(left);
        lastNode.left=null;
        lastNode.right=right;
        return flatDFS(right);
    }
}
