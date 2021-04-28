package bfs.lc291_m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
    /**
     * @param edge: edge[i][0] [1] [2]  start point,end point,value
     * @return: return the second diameter length of the tree
     */
    class Node {
        int val;
        long length;
        List<Integer> neighbors;
        
        public Node(int val){
            this.length=0;
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }
    private Map<Integer, Node> tree = new HashMap<>();
    private Map<String, Integer> edges = new HashMap<>();
    private int longest, longest2;
    public long getSecondDiameter(int[][] edge) {
        if(edge == null || edge[0].length<=1) return 0;
        buildTree(edge);
        FindLongestNode(edge[0][0]);
        //这个地方要跑两边！！！
        FindLongestNode(longest);
        long longest21=tree.get(longest2).length;
        FindLongestNode(longest);

        return Math.max(tree.get(longest2).length,longest21);
    }

    private void FindLongestNode(int node){
        tree.get(node).length=0;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        q.offer(node);
        visited.add(node);

        while(!q.isEmpty()){
            Node currentNode = tree.get(q.poll());
            for(int neighbor:currentNode.neighbors){
                if(!visited.contains(neighbor)){
                    Node neighborNode = tree.get(neighbor);
                    neighborNode.length=currentNode.length+getEdge(currentNode.val,neighborNode.val);
                    FindLongestNode(neighborNode);
                    visited.add(neighbor);
                    q.offer(neighbor);
                }
            }
        }
    }

    private void FindLongestNode(Node node){
        if(node.length>tree.get(longest).length){
            longest2=longest;
            longest=node.val;
        } else if(node.length>tree.get(longest2).length){
            longest2=node.val;
        }
    }

    private void buildTree(int[][] edge){
        for(int i=0;i<edge.length;i++){
            Node n1=getNode(edge[i][0]);
            Node n2=getNode(edge[i][1]);
            setEdge(edge[i][0],edge[i][1],edge[i][2]);

            n1.neighbors.add(n2.val);
            n2.neighbors.add(n1.val);
        }
    }

    private Node getNode(int n){
        if(!tree.containsKey(n)){
            tree.put(n,new Node(n));
        }
        return tree.get(n);
    }

    private void setEdge(int n1, int n2, int edge){
        edges.put(n1+"-"+n2,edge);
    }

    private int getEdge(int n1, int n2){
        if(edges.containsKey(n1+"-"+n2)){
            return edges.get(n1+"-"+n2);
        }
        return edges.get(n2+"-"+n1);
    }
}
