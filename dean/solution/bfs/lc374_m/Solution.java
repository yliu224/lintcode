package bfs.lc374_m;

import java.util.*;

public class Solution {
    /**
     * @param matrix: a matrix of m x n elements
     * @return: an integer list
     */
    private int lenJ;
    private int lenI;
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length==0) return new ArrayList<>();
        lenJ = matrix[0].length;
        lenI = matrix.length;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(getIndex(0,0));
        visited.add(getIndex(0,0));
        List<Integer> result = new ArrayList<>();
        result.add(matrix[0][0]);

        while(!q.isEmpty()){
            int index = q.poll();
            int nextIndex = moveTo(index,visited);
            q.add(nextIndex);
            visited.add(nextIndex);

            if(nextIndex==-1){
                break;
            }
            result.add(matrix[getI(nextIndex)][getJ(nextIndex)]);
        }

        return result;

    }

    private int moveTo(int index,Set<Integer> visited){
        int i=getI(index);
        int j=getJ(index);

        if(canMoveRight(index, visited)){
            return getIndex(i,j+1);
        }

        if(canMoveDown(index, visited)){
            return getIndex(i+1,j);
        }

        if(canMoveLeft(index, visited)){
            return getIndex(i,j-1);
        }

        if(canMoveUp(index, visited)){
            return getIndex(i-1,j);
        }

        return -1;
    }

    private boolean canMoveRight(int index, Set<Integer> visited){
        int i = getI(index);
        int j = getJ(index);
        return j+1<=lenJ-1 && !visited.contains(getIndex(i,j+1)) && 
                (i-1<0 || visited.contains(getIndex(i-1,j)));
    }

    private boolean canMoveUp(int index, Set<Integer> visited){
        int i = getI(index);
        int j = getJ(index);
        return i-1>=0 && !visited.contains(getIndex(i-1,j)) && 
                (j-1<0 || visited.contains(getIndex(i,j-1)));
    }

    private boolean canMoveDown(int index, Set<Integer> visited){
        int i = getI(index);
        int j = getJ(index);
        return i+1<=lenI-1 && !visited.contains(getIndex(i+1,j)) && 
                (j+1>lenJ-1 || visited.contains(getIndex(i,j+1)));
    }

    private boolean canMoveLeft(int index, Set<Integer> visited){
        int i = getI(index);
        int j = getJ(index);
        return j-1>=0 && !visited.contains(getIndex(i,j-1)) && 
                (i+1>lenI-1 || visited.contains(getIndex(i+1,j)));
    }

    private int getIndex(int i,int j){
        return i*lenJ+j;
    }

    private int getI(int index){
        return index/lenJ;
    }

    private int getJ(int index){
        return index%lenJ;
    }
}
