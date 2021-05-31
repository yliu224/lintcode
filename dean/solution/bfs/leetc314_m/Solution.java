package bfs.leetc314_m;

import java.util.*;

import datastructure.TreeNode;

public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> relativeIndexQ = new LinkedList<>();     
        List<List<Integer>> list = new LinkedList<>();
        int mostLeft = 0;
        
        list.add(new ArrayList<>(Arrays.asList(root.val)));
        nodeQ.add(root);
        relativeIndexQ.add(0);
        
        while(!nodeQ.isEmpty()){
            TreeNode node = nodeQ.poll();
            int index = relativeIndexQ.poll();
            
            if(node.left!=null){
                nodeQ.add(node.left);
                relativeIndexQ.add(index-1);
                if(index-1<mostLeft){
                    list.add(0, new ArrayList<>());
                    mostLeft = index-1;
                }
                List<Integer> column = list.get(index-1-mostLeft);
                column.add(node.left.val);
            }
            if(node.right!=null){
                nodeQ.add(node.right);
                relativeIndexQ.add(index+1);
                if(index+1-mostLeft>list.size()-1){
                    list.add(new ArrayList<>());
                }
                List<Integer> column = list.get(index+1-mostLeft);
                column.add(node.right.val);
            }
        }

        return list;
    }
}
