package bfs_with_state_SPFA.lc1334;

import java.util.*;

public class Solution {
    class Node {
        int val;
        Map<Integer, Integer> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new HashMap<>();
        }
    }

    private Map<Integer, Node> graph = new HashMap<>();

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        for (int i = 0; i < edges.length; i++) {
            graph.computeIfAbsent(edges[i][0], Node::new).neighbors.put(edges[i][1], edges[i][2]);
            graph.computeIfAbsent(edges[i][1], Node::new).neighbors.put(edges[i][0], edges[i][2]);
        }
        int min = Integer.MAX_VALUE, minIndex = 0;
        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i)) {
                min = 0;
                minIndex = i;
                continue;
            }
            int tmpMin = getMaxCities(i, distanceThreshold, n);
            if (tmpMin <= min) {
                min = tmpMin;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private int getMaxCities(int start, int threshold, int n) {
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        Queue<int[]> q = new LinkedList<>();
        dis[start] = 0;
        q.offer(new int[]{start, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            Node curNode = graph.get(cur[0]);
            for (Map.Entry<Integer, Integer> next : curNode.neighbors.entrySet()) {
                if (dis[next.getKey()] > cur[1] + next.getValue()) {
                    dis[next.getKey()] = cur[1] + next.getValue();
                    q.offer(new int[]{next.getKey(), dis[next.getKey()]});
                }
            }
        }
        return (int) Arrays.stream(dis).filter(x -> x <= threshold).count();
    }
}
