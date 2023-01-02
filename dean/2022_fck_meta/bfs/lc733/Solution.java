package bfs.lc733;

import java.util.*;

public class Solution {
    private int ilen, jlen;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        ilen = image.length;
        jlen = image[0].length;
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        int start = getIndex(sr, sc);
        q.offer(start);
        result.add(start);
        visited.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int next = move(dir, cur, image, image[sr][sc]);
                if (next != cur && !visited.contains(next)) {
                    q.offer(next);
                    result.add(next);
                    visited.add(next);
                }
            }
        }
        for (int n : result) {
            image[getI(n)][getJ(n)] = color;
        }
        return image;
    }

    private int move(int dir, int cur, int[][] image, int color) {
        int i = getI(cur);
        int j = getJ(cur);
        switch (dir) {
            case 0 -> i++;
            case 1 -> i--;
            case 2 -> j++;
            case 3 -> j--;
        }
        if (i < 0 || j < 0 || i >= ilen || j >= jlen || image[i][j] != color) {
            return cur;
        }
        return getIndex(i, j);
    }

    private int getIndex(int i, int j) {
        return i * jlen + j;
    }

    private int getI(int cur) {
        return cur / jlen;
    }

    private int getJ(int cur) {
        return cur % jlen;
    }
}
