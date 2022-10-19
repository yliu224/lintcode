package bfs.lc695;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {
    private int max = 0;
    private int len, height;

    private int getIndex(int i, int j) {
        return i * len + j;
    }

    private Set<Integer> visited = new HashSet<>();

    private int getI(int index) {
        return index / len;
    }

    private int getJ(int index) {
        return index % len;
    }

    public int maxAreaOfIsland(int[][] grid) {
        len = grid[0].length;
        height = grid.length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] != 0 && !visited.contains(getIndex(i, j))) {
                    //注意什么时候需要加入visited
                    visited.add(getIndex(i, j));
                    BFS(i, j, grid);
                }
            }
        }
        return max;
    }

    private void BFS(int i, int j, int[][] grid) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(getIndex(i, j));
        int size = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            size++;
            for (int dir = 0; dir < 4; dir++) {
                int next = move(cur, dir, grid);
                if (next != cur) {
                    visited.add(next);
                    q.offer(next);
                }
            }
        }
        max = Math.max(max, size);
    }

    private int move(int cur, int dir, int[][] grid) {
        int i = getI(cur);
        int j = getJ(cur);
        switch (dir) {
            //注意这儿的数字0，1，2，3
            case 0 -> i++;
            case 1 -> i--;
            case 2 -> j++;
            case 3 -> j--;
        }
        if (i < 0 || j < 0 || i >= height || j >= len || grid[i][j] == 0 || visited.contains(getIndex(i, j))) {
            return cur;
        }
        return getIndex(i, j);
    }
}
