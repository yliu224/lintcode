package bfs.lc694;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {
    private Set<String> shape = new HashSet<>();
    private Set<Integer> visited = new HashSet<>();
    private int jlen, ilen;

    private int getIndex(int i, int j) {
        return i * jlen + j;
    }

    private int getI(int index) {
        return index / jlen;
    }

    private int getJ(int index) {
        return index % jlen;
    }

    public int numDistinctIslands(int[][] grid) {
        jlen = grid[0].length;
        ilen = grid.length;
        for (int i = 0; i < ilen; i++) {
            for (int j = 0; j < jlen; j++) {
                if (grid[i][j] != 0 && !visited.contains(getIndex(i, j))) {
                    BFS(i, j, grid);
                }
            }
        }
        return shape.size();
    }

    private void BFS(int i, int j, int[][] grid) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>();
        int start = getIndex(i, j);

        q.offer(start);
        visited.add(start);
        //这个记录路径很tricky
        sb.append(start - start);
        sb.append('s');
        sb.append("-");

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int next = move(cur, dir, grid);
                if (next != cur) {
                    visited.add(next);
                    sb.append(cur - start);
                    sb.append(getDir(dir));
                    sb.append("-");
                    q.offer(next);
                }
            }

        }
        shape.add(sb.toString());
    }

    private char getDir(int dir) {
        switch (dir) {
            case 0 -> {
                return 'u';
            }
            case 1 -> {
                return 'd';
            }
            case 2 -> {
                return 'r';
            }
            case 3 -> {
                return 'l';
            }
        }
        throw new RuntimeException("Unknown dir");
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

        if (i < 0 || j < 0 || i >= ilen || j >= jlen || grid[i][j] == 0 || visited.contains(getIndex(i, j))) {
            return cur;
        }
        return getIndex(i, j);
    }
}
