package prefix_sum.lc389_e;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * @param board: the board
     * @return: whether the Sudoku is valid
     */
    public boolean isValidSudoku(char[][] board) {
        return isColumnValid(board) && isRowValid(board) && isSquareValid(board);
    }

    private boolean isColumnValid(char[][] board){
        for(int j=0;j<board[0].length;j++){
            Set<Character> visited = new HashSet<>();
            for(int i=0;i<board.length;i++){
                if(board[i][j]!='.'){
                    if(visited.contains(board[i][j])){
                        return false;
                    } else{ 
                        visited.add(board[i][j]);
                    }               
                }
            }
        }
        return true;
    }

    private boolean isRowValid(char[][] board){
        for(int i=0;i<board.length;i++){
            Set<Character> visited = new HashSet<>();
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]!='.'){
                    if(visited.contains(board[i][j])){
                        return false;
                    } else{ 
                        visited.add(board[i][j]);
                    }  
                }
            }
        }
        return true;
    }

    private boolean isSquareValid(char[][] board){
        int[][] startPoints = new int[][]{{0,0},{0,3},{0,6},{3,0},{3,3},{3,6},{6,0},{6,3},{6,6}};
        for(int index = 0;index<9;index++){
            Set<Character> visited = new HashSet<>();
            int startI = startPoints[index][0];
            int startJ = startPoints[index][1];
            for(int i=startI;i<startI+3;i++){
                for(int j=startJ;j<startJ+3;j++){
                    if(board[i][j]!='.'){
                        if(visited.contains(board[i][j])){
                            return false;
                        } else{ 
                            visited.add(board[i][j]);
                        }  
                    }
                }
            }
        }

        return true;
    }
}