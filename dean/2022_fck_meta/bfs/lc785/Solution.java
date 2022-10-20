package bfs.lc785;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {
    private Set<Integer> set1 = new HashSet<>();
    private Set<Integer> set2 = new HashSet<>();

    public boolean isBipartite(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            if (set1.contains(i) || set2.contains(i) || graph[i] == null || graph[i].length == 0) {
                continue;
            }
            if (!visitSet(i, graph)) {
                return false;
            }
        }

        return true;
    }

    private boolean visitSet(int start, int[][] graph) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        set1.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            Set<Integer> curSet;
            Set<Integer> nonCurSet;

            if (set1.contains(cur)) {
                curSet = set1;
                nonCurSet = set2;
            } else {
                curSet = set2;
                nonCurSet = set1;
            }

            for (int i = 0; i < graph[cur].length; i++) {
                if (curSet.contains(graph[cur][i])) {
                    return false;
                }
                if (!nonCurSet.contains(graph[cur][i])) {
                    nonCurSet.add(graph[cur][i]);
                    q.offer(graph[cur][i]);
                }
            }

        }
        return true;
    }
}
