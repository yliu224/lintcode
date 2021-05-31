package bfs.leetc297_m;

import java.util.*;

import datastructure.TreeNode;

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) return "";
        StringBuilder sb = new StringBuilder();
        TreeNode emptyNode = new TreeNode(Integer.MIN_VALUE);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            sb.append(node.val==Integer.MIN_VALUE?"null":node.val);
            sb.append(",");
            if(node.left!=null){
                q.add(node.left);
            } else if(node.val!=Integer.MIN_VALUE){//注意这儿防止死循环
                q.add(emptyNode);
            }
            if(node.right!=null){
                q.add(node.right);
            } else if(node.val!=Integer.MIN_VALUE){
                q.add(emptyNode);
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")) return null;
        int left=1,right=2;
        String[] nodes = data.split(",");
        //注意val可能要重复，所以一定要用定位来判断他的孩子
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(left<nodes.length && !nodes[left].equals("null")){
                node.left = new TreeNode(Integer.parseInt(nodes[left]));
                q.add(node.left);              
            }
            left = right+1;
            if(right<nodes.length && !nodes[right].equals("null")){
                node.right = new TreeNode(Integer.parseInt(nodes[right]));
                q.add(node.right);             
            }
            right = left+1;
        }
        return root;
    }   
}
