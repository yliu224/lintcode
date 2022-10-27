package bfs.lc1730;

import java.util.*;

public class Solution {
    private int ilen, jlen;

    public int getFood(char[][] grid) {
        ilen = grid.length;
        jlen = grid[0].length;

        int start = 0;
        List<Integer> end = new ArrayList<>();
        for (int i = 0; i < ilen; i++) {
            for (int j = 0; j < jlen; j++) {
                if (grid[i][j] == '*') {
                    start = getIndex(i, j);
                }
            }
        }

        return findShortedPath(start, grid);
    }

    private int findShortedPath(int start, char[][] grid) {
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> visited = new HashMap<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited.put(start, 0);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int stepCount = visited.get(cur);
            for (int dir = 0; dir < 4; dir++) {
                int next = move(dir, cur, grid);
                if (next != cur) {
                    if (!visited.containsKey(next) || visited.get(next) > stepCount + 1) {
                        visited.put(next, stepCount + 1);
                        q.add(next);
                    }
                    if (grid[getI(next)][getJ(next)] == '#') {
                        min = Math.min(stepCount + 1, min);
                    }
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int move(int dir, int cur, char[][] grid) {
        int i = getI(cur);
        int j = getJ(cur);
        switch (dir) {
            case 0 -> i++;
            case 1 -> i--;
            case 2 -> j++;
            case 3 -> j--;
        }
        if (i < 0 || j < 0 || i > ilen - 1 || j > jlen - 1 || grid[i][j] == 'X') {
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
