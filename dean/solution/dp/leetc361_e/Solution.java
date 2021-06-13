package dp.leetc361_e;

public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        int[][] kill = new int[grid.length][grid[0].length];

        int lenI = grid.length;
        int lenJ = grid[0].length;

        // Left
        for (int i = 0; i < lenI; i++) {
            int enemies = 0;
            int preBomb = 0;
            for (int j = 0; j < lenJ; j++) {
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        enemies++;
                    } else {
                        preBomb = preBomb + enemies;
                        kill[i][j] += preBomb;
                        enemies = 0;
                    }
                } else {
                    preBomb = 0;
                    enemies = 0;
                }
            }
        }

        // Right
        for (int i = 0; i < lenI; i++) {
            int enemies = 0;
            int preBomb = 0;
            for (int j = lenJ - 1; j >= 0; j--) {
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        enemies++;
                    } else {
                        preBomb = preBomb + enemies;
                        kill[i][j] += preBomb;
                        enemies = 0;
                    }
                } else {
                    preBomb = 0;
                    enemies = 0;
                }
            }
        }

        // up
        for (int j = 0; j < lenJ; j++) {
            int enemies = 0;
            int preBomb = 0;
            for (int i = 0; i < lenI; i++) {
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        enemies++;
                    } else {
                        preBomb = preBomb + enemies;
                        kill[i][j] += preBomb;
                        enemies = 0;
                    }
                } else {
                    preBomb = 0;
                    enemies = 0;
                }
            }
        }

        // down
        int max = 0;
        for (int j = 0; j < lenJ; j++) {
            int enemies = 0;
            int preBomb = 0;
            for (int i = lenI - 1; i >= 0; i--) {
                if (grid[i][j] != 'W') {
                    if (grid[i][j] == 'E') {
                        enemies++;
                    } else {
                        preBomb = preBomb + enemies;
                        kill[i][j] += preBomb;
                        enemies = 0;
                    }
                } else {
                    preBomb = 0;
                    enemies = 0;
                }
                max = Math.max(max, kill[i][j]);
            }
        }

        return max;
    }

}
