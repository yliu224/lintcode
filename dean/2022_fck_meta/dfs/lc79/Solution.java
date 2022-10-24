package dfs.lc79;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    private Set<Integer> visited = new HashSet<>();
    private int ilen;
    private int jlen;
    private boolean found = false;

    public boolean exist(char[][] board, String word) {
        ilen = board.length;
        jlen = board[0].length;

        for (int i = 0; i < ilen; i++) {
            for (int j = 0; j < jlen; j++) {
                if (board[i][j] == word.charAt(0)) {
                    DFS(getIndex(i, j), board, word, 0);
                }
                if (found) {
                    return true;
                }
            }
        }
        return false;

    }

    private void DFS(int cur, char[][] board, String word, int wi) {
        if (found) {
            return;
        }
        if (wi == word.length() - 1) {
            found = true;
            return;
        }
        visited.add(cur);
        for (int dir = 0; dir < 4; dir++) {
            int next = move(cur, dir, board, word.charAt(wi + 1));
            if (next != cur) {
                DFS(next, board, word, wi + 1);
            }
        }
        visited.remove(cur);
    }

    private int move(int cur, int dir, char[][] board, char c) {
        int i = getI(cur);
        int j = getJ(cur);

        //注意这儿是从0开始！！！
        switch (dir) {
            case 0 -> i++;
            case 1 -> i--;
            case 2 -> j++;
            case 3 -> j--;
        }
        int next = getIndex(i, j);
        if (i < 0 || j < 0 || i >= ilen || j >= jlen || visited.contains(next) || board[i][j] != c) {
            return cur;
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
