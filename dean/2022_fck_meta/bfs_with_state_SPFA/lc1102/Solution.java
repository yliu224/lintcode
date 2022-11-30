package bfs_with_state_SPFA.lc1102;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {
    private Set<Integer> visited = new HashSet<>();
    private int ilen, jlen;

    public int maximumMinimumPath(int[][] grid) {
        ilen = grid.length;
        jlen = grid[0].length;
        //用PriorityQueue 去贪心
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> grid[getI(b)][getJ(b)] - grid[getI(a)][getJ(a)]);
        visited.add(0);
        pq.add(0);

        int min = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            int i = getI(cur);
            int j = getJ(cur);
            min = Math.min(min, grid[i][j]);
            if (i == ilen - 1 && j == jlen - 1) {
                return min;
            }
            for (int dir = 0; dir < 4; dir++) {
                int next = move(cur, dir);
                if (next != cur) {
                    pq.add(next);
                    visited.add(next);
                }
            }
        }
        return min;
    }

    private int move(int cur, int dir) {
        int i = getI(cur);
        int j = getJ(cur);
        switch (dir) {
            case 0 -> i++;
            case 1 -> i--;
            case 2 -> j++;
            case 3 -> j--;
        }
        int next = getIndex(i, j);
        if (i < 0 || j < 0 || i >= ilen || j >= jlen || visited.contains(next)) {
            return cur;
        }
        return next;
    }

    private int getIndex(int i, int j) {
        return i * jlen + j;
    }

    private int getI(int index) {
        return index / jlen;
    }

    private int getJ(int index) {
        return index % jlen;
    }
}
