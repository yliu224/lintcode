package bfs.lc615_h;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//TopSort
public class Solution {
    static class Node{
        public int value;
        public List<Integer> in;
        public List<Integer> out;

        public Node(int value){
            this.value = value;
            this.out = new ArrayList<>();
            this.in=new ArrayList<>();
        }

        public void addIn(int node){
            if(this.in.contains(node)){
                return;
            }
            this.in.add(node);
        }

        public void removeIn(int node){
            int index = this.in.indexOf(node);
            if(index!=-1){
                this.in.remove(index);
            }
        }

        public void addOut(int node){
            if(this.out.contains(node)){
                return;
            }
            this.out.add(node);
        }
    }

    /**
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    private Map<Integer, Node> graph=new HashMap<>();
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // write your code here
        if(numCourses ==1 || prerequisites.length==0) return true;
        Set<Integer> visited = new HashSet<>();
        buildGraph(prerequisites);
        Queue<Integer> q = getStartNodes();
        if(q.size()==0) return false; //注意这个条件!!!
        visited.addAll(q);

        while(!q.isEmpty()){
            Node current = graph.get(q.poll());
            for(int out:current.out){
                Node neighbor = graph.get(out);
                if(visited.contains(neighbor.value)){
                    return false;
                }
                neighbor.removeIn(current.value);//要先减去在入队列
                if(neighbor.in.size()==0){
                    q.offer(neighbor.value);
                    visited.add(neighbor.value);
                }
            }
        }

        return visited.size()==graph.size();//注意这个条件!!!
    }

    private Queue<Integer> getStartNodes(){
        Queue<Integer> q = new LinkedList<>();
        for(Node node:graph.values()){
            if(node.in.size()==0){
                q.offer(node.value);
            }
        }
        return q;
    }

    private void buildGraph(int[][] prerequisites){
        for(int i=0;i<prerequisites.length;i++){
            Node a = getNode(prerequisites[i][0]);
            Node b = getNode(prerequisites[i][1]);

            a.addIn(b.value);
            b.addOut(a.value);
        }
    }

    private Node getNode(int n){
        if(!graph.containsKey(n)){
            graph.put(n, new Node(n));
        }
        return graph.get(n);
    }
}
