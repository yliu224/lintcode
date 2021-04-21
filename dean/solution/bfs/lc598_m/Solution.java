package bfs.lc598_m;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    private Set<Integer> visited = new HashSet<>();
    private int len; // 注意这个len是j的len！！
    public int zombie(int[][] grid) {
        if(grid == null || grid.length==0) return -1;

        int steps = 0;
        len = grid[0].length;
        Queue<Integer> queueVisiting = initTheMap(grid);
        Queue<Integer> queueToBeVisited = new LinkedList<>();

        while(!queueVisiting.isEmpty() || !queueToBeVisited.isEmpty()){
            while(!queueVisiting.isEmpty()){
                int index = queueVisiting.poll();
                int i = getI(index);
                int j = getJ(index);

                if(canMoveTo(i, j, 0, 1, grid)){
                    visited.add(getIndex(i,j+1));
                    queueToBeVisited.offer(getIndex(i,j+1));
                }

                if(canMoveTo(i, j, 0, -1, grid)){
                    visited.add(getIndex(i,j-1));
                    queueToBeVisited.offer(getIndex(i,j-1));
                }

                if(canMoveTo(i, j, 1, 0, grid)){
                    visited.add(getIndex(i+1,j));
                    queueToBeVisited.offer(getIndex(i+1,j));
                }

                if(canMoveTo(i, j, -1, 0, grid)){
                    visited.add(getIndex(i-1,j));
                    queueToBeVisited.offer(getIndex(i-1,j));
                }
            }
            steps++;
            queueVisiting.addAll(queueToBeVisited);
            queueToBeVisited.clear();
        }

        return isAllPeopleZombies(grid)? steps-1 : -1;
    }

    private boolean canMoveTo(int i,int j,int offSetI, int offSetJ, int[][] grid){
        if(i+offSetI<0 || i+offSetI>grid.length-1){
            return false;
        }

        if(j+offSetJ<0 || j+offSetJ>grid[0].length-1){
            return false;
        }

        if(visited.contains(getIndex(i+offSetI,j+offSetJ))){
            return false;
        }

        if(grid[i+offSetI][j+offSetJ]!=0){
            return false;
        }

        return true;
    }

    private Queue<Integer> initTheMap(int[][] grid){
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    visited.add(getIndex(i, j));
                    q.offer(getIndex(i, j));
                }
            }
        }
        return q;
    }

    private int getIndex(int i,int j){
        return i*len+j;
    }

    private int getI(int index){
        return index/len;
    }

    private int getJ(int index){
        return index%len;
    }

    private boolean isAllPeopleZombies(int[][] grid){
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==0 && !visited.contains(getIndex(i,j))){
                    return false;
                }
            }
        }
        return true;        
    }
}
