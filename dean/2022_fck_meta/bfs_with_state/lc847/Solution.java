package bfs_with_state.lc847;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {
    public int shortestPathLength(int[][] graph) {
        if(graph.length<=1){
            return 0;
        }
        //这儿要用Array，不能用object.equals() 那个很慢
        Set<Integer>[] visited = new HashSet[graph.length];
        Queue<Pair> q = new ArrayDeque<>();
        Queue<Pair> swapQ = new ArrayDeque<>();

        int finalState = 0;
        for(int i=0;i<graph.length;i++){
            //注意这儿是像左移动
            Pair p = new Pair(i,1<<i);
            q.offer(p);
            visited[i] = new HashSet<>();
            visited[i].add(p.state);
            finalState = finalState | (1<<i);
        }

        int step=1;
        while(!q.isEmpty()) {
            while (!q.isEmpty()) {
                Pair p = q.poll();
                for (int i = 0; i < graph[p.node].length; i++) {
                    Pair next = p.nextState(graph[p.node][i]);
                    if(next.state==finalState){
                        return step;
                    }
                    if (!visited[next.node].contains(next.state)) {
                        visited[next.node].add(next.state);
                        swapQ.offer(next);
                    }
                }
            }
            step++;
            q.addAll(swapQ);
            swapQ.clear();
        }
        return step;
    }
    class Pair{
        int node;
        int state;

        Pair(int node,int state){
            this.node = node;
            this.state = state;
        }

        Pair nextState(int n){
            return new Pair(n,this.state | (1<<n));
        }
    }
}
