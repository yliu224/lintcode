package bfs.lc116;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Solution {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
    public Node connect(Node root) {
        if(root == null){
            return root;
        }
        Queue<Node> q = new ArrayDeque<>();
        Queue<Node> swapq = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            buildNext(q.stream().toList());
            while(!q.isEmpty()){
                Node n = q.poll();
                if(n.left!=null){
                    swapq.offer(n.left);
                }
                if(n.right!=null){
                    swapq.offer(n.right);
                }
            }
            q.addAll(swapq);
            swapq.clear();
        }
        return root;
    }

    private void buildNext(List<Node> q){
        for(int i=0;i<q.size();i++){
            q.get(i).next = i+1<q.size()?q.get(i+1):null;
        }
    }
}
