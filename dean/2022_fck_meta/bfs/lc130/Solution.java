package bfs.lc130;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

//注意整个结构
//move() getIndex() Set<Integer> visited 要记住
public class Solution {
    private int len;
    private int height;
    private Set<Integer> visited = new HashSet<>();
    private Queue<Integer> path = new ArrayDeque<>();
    private int getIndex(int i,int j){
        return i*len+j;
    }

    private int getI(int index){
        return index/len;
    }

    private int getJ(int index){
        return index%len;
    }

    public void solve(char[][] board) {
        len = board[0].length;
        height = board.length;

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(canVisit(i,j,board)){
                    if(isValidShape(i,j,board)){
                        flip(board);
                    }
                }
            }
        }
    }

    private void flip(char[][] board){
        while(!path.isEmpty()){
            int current = path.poll();
            board[getI(current)][getJ(current)]='X';
        }
    }

    private boolean canVisit(int i,int j,char[][] board){
        if(visited.contains(getIndex(i,j))){
            return false;
        }
        visited.add(getIndex(i,j));
        return board[i][j]=='O';
    }

    private boolean isValidShape(int i,int j,char[][] board){
        boolean isValid = isValidPoint(getIndex(i,j));
        path.clear();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(getIndex(i,j));
        path.offer(getIndex(i,j));
        while(!q.isEmpty()){
            int current = q.poll();
            for(int direction=0;direction<4;direction++){
                int next = move(current,direction,board);
                if(next!=current){
                    q.offer(next);
                    path.offer(next);
                    visited.add(next);
                    isValid &= isValidPoint(next);
                }
            }
        }
        return isValid;
    }
    private boolean isValidPoint(int index){
        int i = getI(index);
        int j = getJ(index);
        if(i==0 || j==0 || i==height-1 || j == len-1){
            return false;
        }
        return true;
    }
    private int move(int current,int dir,char[][] board){
        int i=getI(current);
        int j=getJ(current);
        switch (dir) {
            case 0 ->//move up
                    j += 1;
            case 1 ->//move down
                    j -= 1;
            case 2 ->//move left
                    i -= 1;
            case 3 ->//move right
                    i += 1;
        }
        if(i<0 || j<0 || i>=height || j>=len || board[i][j]=='X' || visited.contains(getIndex(i,j))){
            return current;
        }
        return getIndex(i,j);
    }
}
