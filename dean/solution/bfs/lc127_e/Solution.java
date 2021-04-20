package bfs.lc127_e;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Definition for Directed graph. class DirectedGraphNode { int label;
 * List<DirectedGraphNode> neighbors; DirectedGraphNode(int x) { label = x;
 * neighbors = new ArrayList<DirectedGraphNode>(); } }
 */

public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    private Queue<DirectedGraphNode> queue = new LinkedList<>();
    private Set<Integer> visited = new HashSet<>();

    public ArrayList<DirectedGraphNode> topSort() {
        DirectedGraphNode n5 = new DirectedGraphNode(5);
        DirectedGraphNode n4 = new DirectedGraphNode(4);
        DirectedGraphNode n3 = new DirectedGraphNode(3, new ArrayList<DirectedGraphNode>(Arrays.asList(n5, n4)));
        DirectedGraphNode n2 = new DirectedGraphNode(2, new ArrayList<DirectedGraphNode>(Arrays.asList(n5, n4)));
        DirectedGraphNode n1 = new DirectedGraphNode(1, new ArrayList<DirectedGraphNode>(Arrays.asList(n4)));
        DirectedGraphNode n0 = new DirectedGraphNode(0, new ArrayList<DirectedGraphNode>(Arrays.asList(n1, n2, n3)));
        ArrayList<DirectedGraphNode> graph = new ArrayList<>(Arrays.asList(n0, n1, n2, n3, n4, n5));

        ArrayList<DirectedGraphNode> topOrder = new ArrayList<>();
        Queue<DirectedGraphNode> roots = findRoots(graph);
        Map<Integer,Integer> inComingEdge = buildInComingEdge(graph);

        // BFS TopSort
        queue.addAll(roots);
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            topOrder.add(node);

            for (DirectedGraphNode neighbor : node.neighbors) {
                if (!visited.contains(neighbor.label) && inComingEdge.get(neighbor.label)==1) {
                    queue.offer(neighbor);
                    visited.add(neighbor.label);
                } else{
                    inComingEdge.put(neighbor.label,inComingEdge.get(neighbor.label)-1);
                }
            }
        }

        return topOrder;

    }

    //两个关键的函数
    //找Root
    private Queue<DirectedGraphNode> findRoots(ArrayList<DirectedGraphNode> graph) {
        Map<Integer, Integer> numberOfReversedChild = new HashMap<>();
        Map<Integer, DirectedGraphNode> graphMap = new HashMap<>();
        Queue<DirectedGraphNode> roots = new LinkedList<>();

        for (DirectedGraphNode n : graph) {
            if (!numberOfReversedChild.containsKey(n.label)) {
                numberOfReversedChild.put(n.label, 0);
            }
            graphMap.put(n.label, n);
            for (DirectedGraphNode c : n.neighbors) {
                if (numberOfReversedChild.containsKey(c.label)) {
                    numberOfReversedChild.put(c.label, numberOfReversedChild.get(c.label) + 1);
                } else {
                    numberOfReversedChild.put(c.label, 1);
                }
            }
        }

        for (Integer i : numberOfReversedChild.keySet()) {
            if (numberOfReversedChild.get(i) == 0) {
                roots.offer(graphMap.get(i));
            }
        }

        return roots;
    }

    //建立和边相关的图
    private Map<Integer,Integer> buildInComingEdge(ArrayList<DirectedGraphNode> graph){
        Map<Integer,Integer> inComingEdge = new HashMap<>();
        for(DirectedGraphNode node: graph){
            for(DirectedGraphNode neighbor:node.neighbors){
                if(inComingEdge.containsKey(neighbor.label)){
                    inComingEdge.put(neighbor.label,inComingEdge.get(neighbor.label)+1);
                } else{
                    inComingEdge.put(neighbor.label,1);
                }
            }
        }
        return inComingEdge;
    }

    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }

        DirectedGraphNode(int x, List<DirectedGraphNode> neighbors) {
            this.label = x;
            this.neighbors = neighbors;
        }
    }
}