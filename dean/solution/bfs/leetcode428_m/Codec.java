package bfs.leetcode428_m;

import java.util.*;

public class Codec {
    class Node {
        public int val;
        public List<Node> children;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root==null) return "";
        Queue<Node> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        q.add(root);
        
        while(!q.isEmpty()){
            Node node = q.poll();
            sb.append(node.val);
            sb.append("("+node.children.size()+")");
            sb.append(",");
            if(node.children!=null){
                q.addAll(node.children);
            }
        }
        
        return sb.toString();
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.equals("")) return null;
        String[] nodes = data.split(",");
        Queue<Node> q=new LinkedList<>();
        Queue<Integer> size = new LinkedList<>();
        int childIndex=1;
        Node root = new Node(getVal(nodes[0]),new ArrayList<>());
        q.add(root);
        size.add(getChildrenSize(nodes[0]));
                                     
        while(!q.isEmpty()){
            Node node = q.poll();
            int end = childIndex+size.poll();
            while(childIndex<end){
                Node child = new Node(getVal(nodes[childIndex]),new ArrayList<>());
                node.children.add(child);
                q.add(child);
                size.add(getChildrenSize(nodes[childIndex]));
                childIndex++;
            }
        }              
        return root;                             
    }
    
    private int getVal(String node){
        return Integer.parseInt(node.substring(0,node.indexOf("(")));
    }
    
    private int getChildrenSize(String node){
        return Integer.parseInt(node.substring(node.indexOf("(")+1,node.length()-1));
    }
}
