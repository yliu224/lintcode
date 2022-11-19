package dfs.lc1219;

import java.util.*;

public class Solution {
    private int ilen, jlen;

    public int getMaximumGold(int[][] grid) {
        ilen = grid.length;
        jlen = grid[0].length;
        int max = 0;
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    int cur = getIndex(i, j);
                    visited.add(cur);
                    max = Math.max(max, DFS(grid, cur, visited));
                    visited.remove(cur);
                }
            }
        }
        return max;
    }

    private int DFS(int[][] grid, int cur, Set<Integer> visited) {
        int curVal = grid[getI(cur)][getJ(cur)];
        int max = curVal;
        for (int dir = 0; dir < 4; dir++) {
            int next = move(dir, cur, grid);
            if (next != cur && !visited.contains(next)) {
                visited.add(next);
                max = Math.max(max, curVal + DFS(grid, next, visited));
                visited.remove(next);
            }
        }
        return max;
    }

    private int move(int dir, int cur, int[][] grid) {
        int i = getI(cur);
        int j = getJ(cur);
        switch (dir) {
            case 0 -> i++;
            case 1 -> i--;
            case 2 -> j++;
            case 3 -> j--;
        }
        if (i < 0 || j < 0 || i >= ilen || j >= jlen || grid[i][j] == 0) {
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
