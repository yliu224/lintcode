package bfs.lc573_m;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {
    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    private int[][] numberOfVisits;
    private int[][] distance;
    private int len;
    public int shortestDistance(int[][] grid) {
        if(grid==null || grid.length==0) return 0;
        numberOfVisits = new int[grid.length][grid[0].length];
        distance = new int[grid.length][grid[0].length];
        len = grid[0].length;

        List<Integer> houses = getHoues(grid);
        if(houses.size()==0) return 0;
        for(Integer house:houses){
            walkThroughTheMap(house,grid);
        }
        return findMiniDistance(houses.size());
    }

    private void walkThroughTheMap(int house, int[][] grid){
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int step = 0;

        visited.add(house);
        queue.offer(house);
        while(!queue.isEmpty()){
            int qsize = queue.size();
            while(qsize-->0){
                int currenPosition = queue.poll();
                for(int direction = 0;direction<4;direction++){
                    int nextPosition = moveTo(currenPosition,direction,grid);
                    if(canMove(nextPosition,grid) && !visited.contains(nextPosition)){
                        visited.add(nextPosition);
                        queue.offer(nextPosition);
                        distance[getI(nextPosition)][getJ(nextPosition)]+=step+1;
                        numberOfVisits[getI(nextPosition)][getJ(nextPosition)]++;
                    }
                }
            }
            step++;
        }
    }

    private int findMiniDistance(int size){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<distance.length;i++){
            for(int j=0;j<distance[i].length;j++){
                if(numberOfVisits[i][j]==size && distance[i][j]!=0){
                    min = Math.min(min,distance[i][j]);
                }
            }
        }
        return min==Integer.MAX_VALUE ? -1 : min;
    }

    private int moveTo(int index, int direction,int[][] grid){
        int i=getI(index);
        int j=getJ(index);
        switch(direction){
            case 0://Move up
                if(i==0) return -1;
                return getIndex(i-1, j);
            case 1://Move down
                if(i==grid.length-1) return -1;
                return getIndex(i+1, j);
            case 2://Move left
                if(j==0) return -1;
                return getIndex(i,j-1);
            case 3://Move right
                if(j==grid[0].length-1) return -1;
                return getIndex(i,j+1);
            default:
                throw new RuntimeException(String.format("Direction %s not correct", direction));
        }
    }

    private boolean canMove(int index,int[][] grid) {
        if(index<0){
            return false;
        }
        return grid[getI(index)][getJ(index)]==0;
    }

    private List<Integer> getHoues(int[][] grid){
        List<Integer> houses = new ArrayList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    houses.add(getIndex(i, j));
                }
            }
        }
        return houses;
    }

    private int getI(int index){
        return index/len;
    }

    private int getJ(int index){
        return index%len;
    }

    private int getIndex(int i,int j){
        return i*len+j;
    }
}
