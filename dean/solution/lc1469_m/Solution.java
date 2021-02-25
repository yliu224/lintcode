package dean.solution.lc1469_m;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    static class Node{
        public int value;
        public Map<Integer,Integer> neighbors;

        public Node(int value){
            this.value = value;
            this.neighbors = new HashMap<>();
        }

        public void setNeighbor(int node,int length){
            this.neighbors.put(node,length);
        }
    }

    int max;
    /**
     * @param n: The number of nodes
     * @param starts: One point of the edge
     * @param ends: Another point of the edge
     * @param lens: The length of the edge
     * @return: Return the length of longest path on the tree.
     */
    public int longestPath(int n, int[] starts, int[] ends, int[] lens) {
        Map<Integer,Node> tree = new HashMap<>();
        for(int i =0;i<n-1;i++){
            Node nodeA = getNode(starts[i],tree);
            Node nodeB = getNode(ends[i],tree);
            nodeA.setNeighbor(nodeB.value,lens[i]);
            nodeB.setNeighbor(nodeA.value,lens[i]);

            tree.put(nodeA.value,nodeA);
            tree.put(nodeB.value,nodeB);

        }
        max = Integer.MIN_VALUE;

        findLongestPath(tree.get(starts[0]),-1,tree);

        return max;
    }

    private int findLongestPath(Node node, int parent, Map<Integer,Node> tree){
        //[WARN] Be restrict on the exit condition
        //Think twice
        if(node.neighbors.size()==1 && node.neighbors.containsKey(parent)){
            return 0;
        }

        int longestPath = 0, secondLongestPath = 0;
        for(Map.Entry<Integer,Integer> e:node.neighbors.entrySet()){
            //[WARN] Don't forget the parent
            if(e.getKey()==parent){
                continue;
            }
            int longestSubPath = findLongestPath(tree.get(e.getKey()),node.value,tree)+e.getValue();

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


    public Node getNode(int value,Map<Integer,Node> tree){
        //[WARN] Dont write to ***!tree.containsKey(value)***
        if(tree.containsKey(value)){
            return tree.get(value);
        } else{
            return new Node(value);
        }
    }
}
