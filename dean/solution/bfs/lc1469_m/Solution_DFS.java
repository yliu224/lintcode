package bfs.lc1469_m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_DFS {
    static class Node{
        public int value;
        public int longestEdge,secondeLongestEdge;
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
        for(int i =0;i<n-1;i++){
            Node nodeA = getNode(starts[i]);
            Node nodeB = getNode(ends[i]);
            nodeA.addNeighbor(nodeB.value);
            nodeB.addNeighbor(nodeA.value);
            edges.put(String.valueOf(nodeA.value+"-"+nodeB.value), lens[i]);
        }
        max = Integer.MIN_VALUE;

        findLongestPath(tree.get(starts[0]),-1);

        return max;
    }

    //Stack overflow
    private int findLongestPath(Node node, int parent){
        //[WARN] Be restrict on the exit condition
        //Think twice
        if(node.neighbors.size()==1 && node.neighbors.get(0)==parent){
            return 0;
        }

        int longestPath = 0, secondLongestPath = 0;
        for(Integer neighbor:node.neighbors){
            //[WARN] Don't forget the parent
            if(neighbor==parent){
                continue;
            }
            int longestSubPath = findLongestPath(tree.get(neighbor),node.value)+getEdge(node.value, neighbor);

            //update longestPath and secondeLongestPath
            if(longestSubPath>longestPath){
                //[WARN] be careful on the order
                //longestPath = longestSubPath;
                //secondLongestPath = longestPath; WRONG ORDER!!!
                secondLongestPath = longestPath;
                longestPath = longestSubPath;
            } else if(longestSubPath > secondLongestPath){
                secondLongestPath = longestSubPath;
            }


        }
        this.max = Math.max(max,longestPath+secondLongestPath);

        return longestPath;
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
