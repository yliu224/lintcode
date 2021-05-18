package bfs.lc69_e;

import java.util.*;

import datastructure.TreeNode;

public class Solution {
    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> nextLevel = new LinkedList<>();
        q.add(root);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>(Arrays.asList(root.val)));

        while(!q.isEmpty()){
            nextLevel.clear();
            List<Integer> children = new ArrayList<>();
            while(!q.isEmpty()){
                TreeNode node = q.poll();
                if(node.left!=null){
                    children.add(node.left.val);
                    nextLevel.add(node.left);
                }
                if(node.right!=null){
                    children.add(node.right.val);
                    nextLevel.add(node.right);
                }
            }
            if(children.size()!=0){
                result.add(children);
            }
            q.addAll(nextLevel);
        }


        return result;
    }
}
