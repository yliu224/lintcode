package bfs.lc1469_m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution_BFS {
    static class Node{
        public int value;
        public int length;
        public List<Integer> neighbors;

        public Node(int value){
            this.value = value;
            this.neighbors = new ArrayList<>();
        }

        public void addNeighbor(int node){
            this.neighbors.add(node);
        }
    }

    private int max;
    private Map<String,Integer> edges = new HashMap<>();
    private Map<Integer,Node> tree = new HashMap<>();
    /**
     * @param n: The number of nodes
     * @param starts: One point of the edge
     * @param ends: Another point of the edge
     * @param lens: The length of the edge
     * @return: Return the length of longest path on the tree.
     */
    public int longestPath(int n, int[] starts, int[] ends, int[] lens) {
        if(n==1) return 0;
        buildTree(n,starts,ends,lens);

        int longestNode = BFS(tree.get(starts[0]));
        BFS(tree.get(longestNode));
        return max;
    }

    private void buildTree(int n, int[] starts, int[] ends, int[] lens){
        for(int i =0;i<n-1;i++){
            Node nodeA = getNode(starts[i]);
            Node nodeB = getNode(ends[i]);
            nodeA.addNeighbor(nodeB.value);
            nodeB.addNeighbor(nodeA.value);
            edges.put(String.valueOf(nodeA.value+"-"+nodeB.value), lens[i]);
        }
    }

    private Integer BFS(Node node){
        max = Integer.MIN_VALUE;
        int longestNode = 0;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        q.offer(node.value);
        visited.add(node.value);
        node.length=0;

        while(!q.isEmpty()){
            int current = q.poll();
            for(int neighbor:tree.get(current).neighbors){
                if(!visited.contains(neighbor)){
                    Node neighborNode = tree.get(neighbor);
                    q.offer(neighbor);
                    visited.add(neighbor);
                    neighborNode.length=tree.get(current).length+getEdge(current, neighbor);

                    if(max<neighborNode.length){
                        max = neighborNode.length;
                        longestNode = neighbor;
                    }
                }
            }
        }
        return longestNode;
    }

    private Node getNode(int value){
        if(!tree.containsKey(value)){
            tree.put(value,new Node(value));
        }
        return tree.get(value);
    }

    private int getEdge(int a,int b){
        return edges.get(a+"-"+b) == null?
            edges.get(b+"-"+a):
            edges.get(a+"-"+b);
    }
}
