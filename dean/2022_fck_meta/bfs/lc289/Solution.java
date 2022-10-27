package bfs.lc289;

public class Solution {
    public void gameOfLife(int[][] board) {
        int ilen = board.length;
        int jlen = board[0].length;
        int[][] nextGen = new int[ilen][jlen];

        for (int i = 0; i < ilen; i++) {
            for (int j = 0; j < jlen; j++) {
                if (board[i][j] == 0) {
                    nextGen[i][j] = reproduce(i, j, board);
                } else {
                    nextGen[i][j] = deadOrAlive(i, j, board);
                }
            }
        }

        for (int i = 0; i < ilen; i++) {
            for (int j = 0; j < jlen; j++) {
                board[i][j] = nextGen[i][j];
            }
        }
    }

    private int reproduce(int i, int j, int[][] board) {
        return getOnes(i, j, board) == 3 ? 1 : 0;
    }

    private int deadOrAlive(int i, int j, int[][] board) {
        int ones = getOnes(i, j, board);

        if (ones < 2 || ones > 3) {
            return 0;
        }
        return 1;
    }

    private int getOnes(int i, int j, int[][] board) {
        int sum = 0;
        for (int dir = 0; dir < 8; dir++) {
            int[] next = move(dir, i, j, board.length, board[0].length);
            if (next[0] == i && next[1] == j) {
                continue;
            }
            if (board[next[0]][next[1]] == 1) {
                sum++;
            }
        }
        return sum;
    }

    private int[] move(int dir, int i, int j, int ilen, int jlen) {
        int ci = i;
        int cj = j;
        switch (dir) {
            case 0 -> {
                i++;
            }
            case 1 -> {
                i--;
            }
            case 2 -> {
                j++;
            }
            case 3 -> {
                j--;
            }
            case 4 -> {
                i++;
                j++;
            }
            case 5 -> {
                i++;
                j--;
            }
            case 6 -> {
                i--;
                j++;
            }
            case 7 -> {
                i--;
                j--;
            }

        }
        if (i < 0 || j < 0 || i > ilen - 1 || j > jlen - 1) {
            return new int[]{ci, cj};
        }
        return new int[]{i, j};
    }
}
