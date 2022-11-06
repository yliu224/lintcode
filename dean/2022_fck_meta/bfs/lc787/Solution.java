package bfs.lc787;

import java.util.*;

public class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Integer>[] flightsMap = new HashMap[n];
        for (int i = 0; i < flightsMap.length; i++) {
            flightsMap[i] = new HashMap<>();
        }
        for (int[] f : flights) {
            flightsMap[f[0]].put(f[1], f[2]);
        }

        //LinkedList就可以了，效率很高
        //记住状态一定要存在Q里面
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> swap = new LinkedList<>();
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        q.offer(new int[]{src, 0});
        cost[src] = 0;

        while (!q.isEmpty() && k >= 0) {
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (Map.Entry<Integer, Integer> e : flightsMap[cur[0]].entrySet()) {
                    if (cost[e.getKey()] > cost[cur[0]] + e.getValue()) {
                        cost[e.getKey()] = cur[1] + e.getValue();
                        swap.offer(new int[]{e.getKey(), cur[1] + e.getValue()});
                    }
                }
            }
            q.addAll(swap);
            swap.clear();
            k--;
        }
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }
}
