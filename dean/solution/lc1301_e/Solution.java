package lc1301_e;

public class Solution {
    /**
     * @param board: the given board
     * @return: nothing
     */
    public void gameOfLife(int[][] board) {
        int[][] nextGen = new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                nextGen[i][j]=goNextGen(board,i,j);
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=nextGen[i][j];
            }
        }
    }

    private int goNextGen(int[][] board,int i,int j){
        int neighbors = 0;
        for(int step=0;step<8;step++){
            int neighbor = moveTo(step,i,j,board);
            if(neighbor==1){
                neighbors++;
            }
        }

        if(board[i][j]==0){
            if(neighbors==3){
                return 1;
            }
            return 0;
        } else{
            if(neighbors<2){
                return 0;
            } else if(neighbors==2 || neighbors == 3){
                return 1;
            } else if(neighbors>3){
                return 0;
            }
            throw new RuntimeException("Unknow state for given neighbor count"+neighbors);
        }
    }

    private int moveTo(int step, int i,int j,int [][] board){
        int lenI=board.length;
        int lenJ=board[0].length;
        switch(step){
            case 0://move up
                if(i-1>=0){
                    return board[i-1][j];
                }
                return -1;
            case 1://move down
                if(i+1<=lenI-1){
                    return board[i+1][j];
                }
                return -1;
            case 2://move left
                if(j-1>=0){
                    return board[i][j-1];
                }
                return -1;            
            case 3://move right
                if(j+1<=lenJ-1){
                    return board[i][j+1];
                }
                return -1;                
            case 4://move top left
                if(i-1>=0 && j-1>=0){
                    return board[i-1][j-1];
                }
                return -1;              
            case 5://move top right
                if(i-1>=0 && j+1<=lenJ-1){
                    return board[i-1][j+1];
                }
                return -1;  
            case 6://move bottom left
                if(i+1<=lenI-1 && j-1>=0){
                    return board[i+1][j-1];
                }
                return -1;  
            case 7://move bottom right
                if(i+1<=lenI-1 && j+1<=lenJ-1){
                    return board[i+1][j+1];
                }
                return -1;  
            default:
                throw new RuntimeException("Unknown step index ["+step+"]");
        }
    }
}
