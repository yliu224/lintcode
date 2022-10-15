package bfs.lc505;

import java.util.*;

public class Solution {
    private int len;
    private int height;
    private Set<Integer> visited = new HashSet<>();
    private Queue<Integer> q = new ArrayDeque<>();
    private Map<Integer, Integer> minPath = new HashMap<>();

    private int getIndex(int i, int j) {
        return i * len + j;
    }

    private int getI(int index) {
        return index / len;
    }

    private int getJ(int index) {
        return index % len;
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        len = maze[0].length;
        height = maze.length;
        int end = getIndex(destination[0], destination[1]);
        int starts = getIndex(start[0], start[1]);
        minPath.put(starts, 0);

        q.offer(starts);
        int min = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int current = q.poll();
            int currentMin = minPath.get(current);
            for (int dir = 0; dir < 4; dir++) {
                int next = move(current, dir, maze);
                //注意这儿不能return，return的时候，queue可能不为空
                if (next == end) {
                    min = Math.min(min, currentMin + getLen(current, next));
                }
                if (next != current) {
                    q.offer(next);
                    visited.add(next);
                    minPath.put(next, currentMin + getLen(current, next));
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int getLen(int current, int next) {
        return Math.abs(getI(current) - getI(next)) + Math.abs(getJ(current) - getJ(next));
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
        int next = getIndex(i, j);
        if (!visited.contains(next) || (minPath.get(current) + getLen(current, next) < minPath.getOrDefault(next, Integer.MAX_VALUE))) {
            return next;
        }
        return current;
    }
}
