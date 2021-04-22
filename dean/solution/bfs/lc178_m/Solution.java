package bfs.lc178_m;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    private Queue<String> queue = new LinkedList<>();
    private Set<String> visited = new HashSet<>();
    private String FINAL_STATE = "123450";
    private String OUT_OF_BOX = "";

    public int slidingPuzzle(int[][] board) {
        String init = encode(board);
        visited.add(init);
        queue.offer(init);
        int step = 0;

        while(!queue.isEmpty()){
            int len=queue.size();
            while(len-->0){//注意这个记录步数的地方
                String currentState = queue.poll();
                if(currentState.equals(FINAL_STATE)){//String要用Equals
                    return step;
                }
                for(int direction=0;direction<4;direction++){
                    String nextState = move(currentState,direction);
                    if(nextState!=OUT_OF_BOX && !visited.contains(nextState)){
                        visited.add(nextState);
                        queue.offer(nextState);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String encode(int[][] board){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    /**
     * Return empty string is the move is unachievable.
     * @param currentState
     * @param direction 0:move 0 up
     *                  1:move 0 down
     *                  2:move 0 right
     *                  3:move 0 left
     * @return
     */
    private String move(String currentState,int direction){
        int zero = currentState.indexOf("0");
        String newState = String.valueOf(currentState);
        char movableItem;
        //!!!以后写步数方向的魔板!!!
        switch(direction){
            case 0:
                if(zero>=0 && zero<=2){
                    return OUT_OF_BOX;
                }
                movableItem = currentState.charAt(zero-3);
                break;
            case 1:
                if(zero>=3 && zero<=5){
                    return OUT_OF_BOX;
                }
                movableItem = currentState.charAt(zero+3);
                break;
            case 2:
                if(zero==2 || zero==5){
                    return OUT_OF_BOX;
                }
                movableItem = currentState.charAt(zero+1);
                break;
            case 3:
                if(zero==0 || zero==3){
                    return OUT_OF_BOX;
                }
                movableItem = currentState.charAt(zero-1);
                break;
            default:
                throw new RuntimeException(String.format("Direction %d is not valid",direction));
        }

        newState = newState.replace(movableItem,'T');
        newState = newState.replace('0',movableItem);
        newState = newState.replace('T', '0');

        return newState;
    }
}
