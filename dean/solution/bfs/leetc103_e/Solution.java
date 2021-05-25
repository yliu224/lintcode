package bfs.leetc103_e;

import java.util.*;

import datastructure.TreeNode;

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root==null) return new ArrayList<>();
        Queue<TreeNode> visitingQ = new LinkedList<>();
        Queue<TreeNode> cacheQ = new LinkedList<>();
        List<List<Integer>> answer = new ArrayList<>();
        boolean goLeft = false;
        
        visitingQ.add(root);
        
        while(!visitingQ.isEmpty()){
            Deque<Integer> layer = new LinkedList<>();
            while(!visitingQ.isEmpty()){
                TreeNode node = visitingQ.poll();
                if(goLeft){
                    layer.addFirst(node.val);
                    
                } else{
                    layer.addLast(node.val);
                }
                if(node.left!=null){
                    cacheQ.add(node.left);
                }
                if(node.right!=null){
                    cacheQ.add(node.right);
                }
            }
            goLeft=!goLeft;
            answer.add((List<Integer>) layer);
            visitingQ.addAll(cacheQ);
            cacheQ.clear();
        }
        return answer;
    }
}
