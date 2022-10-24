package mock_state.lc1275;

public class Solution {
    private int[][] board = new int[3][3];

    public String tictactoe(int[][] moves) {
        for (int i = 0; i < moves.length; i++) {
            if (i % 2 == 0) {
                board[moves[i][0]][moves[i][1]] = 1;
            } else {
                board[moves[i][0]][moves[i][1]] = 2;
            }
        }

        return judge(moves.length);
    }

    private String judge(int steps) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                return convertName(board[i][0]);
            }
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != 0) {
                return convertName(board[0][j]);
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) {
            return convertName(board[0][0]);
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != 0) {
            return convertName(board[2][0]);
        }
        return steps == 9 ? "Draw" : "Pending";
    }

    private String convertName(int n) {
        if (n == 1) {
            return "A";
        }
        if (n == 2) {
            return "B";
        }
        throw new RuntimeException("Unknown name " + n);
    }
}
