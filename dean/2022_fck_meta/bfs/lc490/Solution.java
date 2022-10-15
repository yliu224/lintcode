package bfs.lc490;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {
    private int len;
    private int height;
    private Set<Integer> visited = new HashSet<>();
    private Queue<Integer> q = new ArrayDeque<>();

    private int getIndex(int i, int j) {
        return i * len + j;
    }

    private int getI(int index) {
        return index / len;
    }

    private int getJ(int index) {
        return index % len;
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        len = maze[0].length;
        height = maze.length;
        int end = getIndex(destination[0], destination[1]);

        q.offer(getIndex(start[0], start[1]));
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int next = move(current, dir, maze);
                if (next == end) {
                    return true;
                }
                if (next != current) {
                    q.offer(next);
                    visited.add(next);
                }
            }
        }
        return false;
    }

    private int move(int current, int dir, int[][] maze) {
        int i = getI(current);
        int j = getJ(current);

        switch (dir) {
            case 0 -> {
                while (i + 1 < height && maze[i + 1][j] != 1) {
                    i++;
                }
            }
            case 1 -> {
                while (i - 1 >= 0 && maze[i - 1][j] != 1) {
                    i--;
                }
            }
            case 2 -> {
                while (j + 1 < len && maze[i][j + 1] != 1) {
                    j++;
                }
            }
            case 3 -> {
                while (j - 1 >= 0 && maze[i][j - 1] != 1) {
                    j--;
                }
            }
        }
        if (visited.contains(getIndex(i, j))) {
            return current;
        }
        return getIndex(i, j);

    }
}
