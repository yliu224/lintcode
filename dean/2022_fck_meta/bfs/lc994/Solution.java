package bfs.lc994;

import java.util.(Queue);

public class Solution {
    private Queue<int[]> q = new LinkedList<>();
    private Map<Integer, Integer> visited = new HashMap<>();
    private int ilen, jlen;

    public int orangesRotting(int[][] grid) {
        ilen = grid.length;
        jlen = grid[0].length;
        int total = 0;
        for (int i = 0; i < ilen; i++) {
            for (int j = 0; j < jlen; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[]{getIndex(i, j), 0});
                    visited.put(getIndex(i, j), 0);
                }
                if (grid[i][j] != 0) {
                    total++;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int next = move(cur[0], dir, grid);
                if (next != cur[0] && visited.getOrDefault(next, Integer.MAX_VALUE) > cur[1] + 1) {
                    visited.put(next, cur[1] + 1);
                    q.offer(new int[]{next, cur[1] + 1});
                }
            }
        }
        if (visited.size() != total) {
            return -1;
        }
        if (total == 0) {
            return 0;
        }
        return visited.values().stream().mapToInt(x -> x).max().getAsInt();
    }

    private int move(int cur, int dir, int[][] grid) {
        int i = getI(cur);
        int j = getJ(cur);
        switch (dir) {
            case 0 -> i++;
            case 1 -> i--;
            case 2 -> j++;
            case 3 -> j--;
        }
        if (i < 0 || j < 0 || i >= ilen || j >= jlen || grid[i][j] != 1) {
            return cur;
        }
        return getIndex(i, j);
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
