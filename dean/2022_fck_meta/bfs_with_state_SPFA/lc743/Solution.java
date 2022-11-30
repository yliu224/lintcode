package bfs_with_state_SPFA.lc743;

import java.util.*;

public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], -1);
        }
        for (int i = 0; i < times.length; i++) {
            graph[times[i][0]][times[i][1]] = times[i][2];
        }
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Queue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        distance[k] = 0;
        q.offer(new int[]{k, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 1; i < graph.length; i++) {
                if (graph[cur[0]][i] != -1 && distance[i] > cur[1] + graph[cur[0]][i]) {
                    distance[i] = cur[1] + graph[cur[0]][i];
                    q.offer(new int[]{i, distance[i]});
                }
            }
        }
        int max = -1;
        for (int i = 1; i < distance.length; i++) {
            max = Math.max(max, distance[i]);
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }
}
