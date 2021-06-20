package mock_state.leetc840_e;

import java.util.*;

public class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int lenI = grid.length;
        int lenJ = grid[0].length;
        int count = 0;
        for (int i = 0; i < lenI - 2; i++) {
            for (int j = 0; j < lenJ - 2; j++) {
                if (isValid(grid, i, j)) {
                    System.out.println(i + ":" + j);
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isValid(int[][] grid, int startI, int startJ) {
        // determine if the number is distinct
        Set<Integer> numbers = new HashSet<>();
        for (int i = startI; i < startI + 3; i++) {// 注意这儿是 startI+3
            for (int j = startJ; j < startJ + 3; j++) {// 注意这儿是 startJ+3
                if (grid[i][j] >= 10 || grid[i][j] <= 0) {
                    return false;
                } else if (!numbers.contains(grid[i][j])) {
                    numbers.add(grid[i][j]);
                } else {
                    return false;
                }
            }
        }

        // determin if the diagonals are the same

        int left2Right = grid[startI][startJ] + grid[startI + 1][startJ + 1] + grid[startI + 2][startJ + 2];
        int right2Left = grid[startI][startJ + 2] + grid[startI + 1][startJ + 1] + grid[startI + 2][startJ];

        int c1 = grid[startI][startJ] + grid[startI + 1][startJ] + grid[startI + 2][startJ];
        int c2 = grid[startI][startJ + 1] + grid[startI + 1][startJ + 1] + grid[startI + 2][startJ + 1];
        int c3 = grid[startI][startJ + 2] + grid[startI + 1][startJ + 2] + grid[startI + 2][startJ + 2];

        int r1 = grid[startI][startJ] + grid[startI][startJ + 1] + grid[startI][startJ + 2];
        int r2 = grid[startI + 1][startJ] + grid[startI + 1][startJ + 1] + grid[startI + 1][startJ + 2];
        int r3 = grid[startI + 2][startJ] + grid[startI + 2][startJ + 1] + grid[startI + 2][startJ + 2];

        return left2Right == right2Left && right2Left == c1 && c1 == c2 && c2 == c3 && c3 == r1 && r1 == r2 && r2 == r3;
    }
}
