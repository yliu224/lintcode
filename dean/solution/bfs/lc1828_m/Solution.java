package bfs.lc1828_m;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    private Queue<Integer> queue = new LinkedList<>();
    private Set<Integer> visited = new HashSet<>();
    private int len;
    public boolean lakeEscape(int side_length, int[][] lake_grid, int albert_row, int albert_column, int kuna_row, int kuna_column) {
        len = side_length;
        int kuna = getIndex(kuna_row,kuna_column);
        int albert = getIndex(albert_row,albert_column);
        boolean hasMetKuna = false;

        visited.add(albert);
        queue.offer(albert);

        while(!queue.isEmpty()){
            int index = queue.poll();
            int i = getI(index);
            int j = getJ(index);
            for(int step=0;step<4;step++){
                int nextIndex = move(getNextI(i,step),getNextJ(j,step),step,lake_grid);
                if(isShore(nextIndex)){
                    if(hasMetKuna){
                        return true;
                    }
                } else{
                    if(isSnowBank(nextIndex, lake_grid) && !visited.contains(nextIndex)){//is visited 不要忘了
                        visited.add(nextIndex);
                        queue.offer(nextIndex);
                    }
                    if(nextIndex == kuna){
                        hasMetKuna = true;
                    }
                }
            }
        }
        
        return false;
    }

    private int getIndex(int i,int j){
        //注意这个边界的判断，如果用普通的i*len+j，当
        //超出边界的时候，getI(index) 和 getJ(index)无法得到 
        //out of index 的值 
        if(i<0 || i>len-1 || j<0 || j>len-1){
            return -1;
        }
        return i*len+j;
    }

    private int getI(int index){
        if(index == -1) return -1;
        return index/len;
    }

    private int getJ(int index){
        if(index == -1) return -1;
        return index%len;
    }

    private int getNextI(int i, int step){
        switch(step){
            case 0: return i+1;
            case 1: return i-1;
            default: return i;
        }
    }

    private int getNextJ(int j,int step){
        switch(step){
            case 2: return j+1;
            case 3: return j-1;
            default: return j;
        }
    }

    private int move(int i, int j, int step, int[][] lake_grid){
        while(!isShore(getIndex(i,j)) && lake_grid[i][j]==0){
            i = getNextI(i,step);
            j = getNextJ(j,step);
        }

        return getIndex(i,j);
    }

    private boolean isShore(int index){
        return index == -1;
    }

    private boolean isSnowBank(int index, int[][] lake_grid){
        return !isShore(index) && lake_grid[getI(index)][getJ(index)] == 1;
    }
}