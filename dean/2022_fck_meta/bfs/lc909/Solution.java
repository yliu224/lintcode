package bfs.lc909;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Solution {
    private int ilen, jlen;

    public int snakesAndLadders(int[][] board) {
        ilen = board.length;
        jlen = board[0].length;
        int[] flatBoard = buildFlatBoard(board);
        return findRoute(flatBoard);
    }

    private int findRoute(int[] flatBoard) {
        int min = Integer.MAX_VALUE;
        int end = ilen * jlen;
        Map<Integer, Integer> steps = new HashMap<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        steps.put(1, 0);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int curStep = steps.get(cur);
            for (int i = 1; i <= 6; i++) {
                int next = cur + i;
                if (next > end) {
                    continue;
                }
                if (flatBoard[next] != -1) {
                    next = flatBoard[next];
                }
                if (!steps.containsKey(next) || steps.get(next) > curStep + 1) {
                    q.add(next);
                    steps.put(next, curStep + 1);
                    if (next == end) {
                        min = Math.min(min, curStep + 1);
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int[] buildFlatBoard(int[][] board) {
        int[] flatBoard = new int[ilen * jlen + 1];
        int index = 1;
        for (int i = ilen - 1; i >= 0; i--) {
            if ((ilen - i) % 2 == 1) {
                for (int j = 0; j < jlen; j++) {
                    flatBoard[index++] = board[i][j];
                }
            } else {
                for (int j = jlen - 1; j >= 0; j--) {
                    flatBoard[index++] = board[i][j];
                }
            }
        }
        return flatBoard;
    }
}
