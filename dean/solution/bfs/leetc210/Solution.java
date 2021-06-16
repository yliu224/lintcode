package bfs.leetc210;

import java.util.*;

public class Solution {
    class Node {
        int inCount;
        int val;
        List<Node> children;
        
        public Node(int val){
            this.val = val;
            this.inCount = 0;
            this.children = new ArrayList<>();
        }
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //build graph
        Map<Integer,Node> graph = new HashMap<>();
        for(int i=0;i<numCourses;i++){
            graph.put(i,new Node(i));
        }
        for(int i=0;i<prerequisites.length;i++){
            Node parent = graph.get(prerequisites[i][1]);
            Node child = graph.get(prerequisites[i][0]);
            child.inCount++;
            parent.children.add(child);
            
            graph.put(prerequisites[i][1],parent);
            graph.put(prerequisites[i][0],child);    
        }
        
        //find starting point
        Queue<Node> q = new LinkedList<>();
        for(Node n:graph.values()){
            if(n.inCount==0){
                q.add(n);
            }
        }
        if(q.isEmpty()){
            return new int[0];
        }
        //do top logical sort
        List<Integer> result = new ArrayList<>();
        while(!q.isEmpty()){
            Node cur = q.poll();
            result.add(cur.val);
            
            for(Node n:cur.children){
                n.inCount--;
                if(n.inCount==0){
                    q.add(n);
                }
            }
        }
        
        return result.size()==numCourses?result.stream().mapToInt(i->i).toArray():new int[0];
    }
}
