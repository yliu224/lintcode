package dean.solution.bfs.lc677_e;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    /**
     * @param grid: a 2d boolean array
     * @param k: an integer
     * @return: the number of Islands
     */
    private int len;
    private Set<Integer> visited = new HashSet<>();
    public int numsofIsland(boolean[][] grid, int k) {
        if(k<=0) k=1; 
        if(grid== null || grid.length==0) return 0;

        len = grid[0].length;
        int answer = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(getIslandSize(i,j,grid)>=k){
                    answer++;
                }
            }
        }

        return answer;
    }

    private int getEncodeIndex(int i,int j){
        return i*len+j;
    }

    private int getIndexI(int index){
        return index/len;
    }

    private int getIndexJ(int index){
        return index%len;
    }

    private int getIslandSize(int starti,int startj,boolean[][] grid){
        if(!isValidGrid(starti, startj, 0, 0, grid)){
            return 0;
        }
        int size = 0;
        Queue<Integer> queue = new LinkedList<>();
        visited.add(getEncodeIndex(starti, startj));
        queue.offer(getEncodeIndex(starti, startj));
        size++;

        while(!queue.isEmpty()){
            int index = queue.poll();
            int i = getIndexI(index);
            int j = getIndexJ(index);
            if(isValidGrid(i,j,0,1,grid)){
                visited.add(getEncodeIndex(i, j+1));
                queue.offer(getEncodeIndex(i, j+1));
                size++;
            }
            if(isValidGrid(i,j,0,-1,grid)){
                visited.add(getEncodeIndex(i, j-1));
                queue.offer(getEncodeIndex(i, j-1));
                size++;
            }
            if(isValidGrid(i,j,1,0,grid)){
                visited.add(getEncodeIndex(i+1, j));
                queue.offer(getEncodeIndex(i+1, j));
                size++;
            }
            if(isValidGrid(i,j,-1,0,grid)){
                visited.add(getEncodeIndex(i-1, j));
                queue.offer(getEncodeIndex(i-1, j));
                size++;
            }
        }
        
        return size;
    }

    private boolean isValidGrid(int i, int j, int moveI, int moveJ, boolean[][] grid){
        if(i+moveI<0 || i+moveI>grid.length-1){
            return false;
        }

        if(j+moveJ<0 || j+moveJ>grid[0].length-1){
            return false;
        }

        if(visited.contains(getEncodeIndex(i+moveI,j+moveJ))){
            return false;
        }

        return grid[i+moveI][j+moveJ];
    }
}
