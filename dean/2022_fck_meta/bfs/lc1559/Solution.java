package bfs.lc1559;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    private Set<Integer> visited = new HashSet<>();
    private int ilen, jlen;

    public boolean containsCycle(char[][] grid) {
        ilen = grid.length;
        jlen = grid[0].length;

        for (int i = 0; i < ilen; i++) {
            for (int j = 0; j < jlen; j++) {
                int cur = getIndex(i, j);
                if (!visited.contains(cur)) {
                    if (search(cur, grid)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean search(int start, char[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> localVisited = new HashSet<>();
        q.offer(new int[]{start, start});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int next = move(dir, cur, grid);
                if (next != cur[1]) {
                    if (localVisited.contains(next)) {
                        return true;
                    }
                    localVisited.add(next);
                    visited.add(next);
                    q.offer(new int[]{cur[1], next});
                }
            }
        }
        return false;
    }

    private int move(int dir, int[] cur, char[][] grid) {
        int i = getI(cur[1]);
        int j = getJ(cur[1]);
        char c = grid[i][j];
        switch (dir) {
            case 0 -> i++;
            case 1 -> i--;
            case 2 -> j++;
            case 3 -> j--;
        }
        int next = getIndex(i, j);
        if (i < 0 || j < 0 || i >= ilen || j >= jlen || next == cur[0] || grid[i][j] != c) {
            return cur[1];
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
