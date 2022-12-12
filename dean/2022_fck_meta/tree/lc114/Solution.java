package tree.lc114;

import datastructure.TreeNode;

import java.util.*;

public class Solution {
    public void flatten(TreeNode root) {
        if(root==null){
            return;
        }
        LinkedList<TreeNode> path = new LinkedList<>();
        DFS(root,path);
        Iterator<TreeNode> iterator = path.iterator();
        TreeNode pre = iterator.next();
        while(iterator.hasNext()){
            pre.right = iterator.next();
            pre.left = null;
            pre = pre.right;
        }
        root = path.getFirst();
    }
    void DFS(TreeNode node,LinkedList<TreeNode> path){
        path.addLast(node);
        if(node.left!=null){
            DFS(node.left,path);
        }
        if(node.right!=null){
            DFS(node.right,path);
        }
    }
}
