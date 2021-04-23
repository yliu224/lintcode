package bfs.lc258_m;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
    /**
     * @param arr: the map
     * @return:  the smallest target that satisfies from the upper left corner (0, 0) to the lower right corner (n-1, n-1)
     */
    private Queue<Integer> q = new LinkedList<>();
    private Map<Integer,Integer> visited = new HashMap<>();
    private int len;
    public int mapJump(int[][] arr) {
        if(arr==null || arr[0].length==0) return 0;
        len = arr[0].length;
        q.offer(0);
        visited.put(0,0);

        while(!q.isEmpty()){
            int currentPosition = q.poll();
            for(int direction=0;direction<4;direction++){
                try{
                    int nextPosition = moveTo(currentPosition,direction);
                    if(canMove(currentPosition, nextPosition,arr)){
                        visited.put(nextPosition,getEdgeCost(currentPosition,nextPosition,arr));
                        q.offer(nextPosition);
                    }
                } catch(RuntimeException e){
                    continue;
                }
            }
        }
        return visited.get(getIndex(len-1,len-1));
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

    private int getEdgeCost(int currentPosition,int nextPosition,int[][] arr){
        return Math.max(
            Math.abs(arr[getI(currentPosition)][getJ(currentPosition)]-arr[getI(nextPosition)][getJ(nextPosition)]),
            visited.get(currentPosition));
    }

    private boolean canMove(int currentPosition, int nextPosition,int[][] arr){
        if(!visited.containsKey(nextPosition)){
            return true;
        }

        if(getEdgeCost(currentPosition,nextPosition,arr)<visited.get(nextPosition)){
            return true;
        }

        return false;
    }

    private int moveTo(int position, int direction){
        int i=getI(position);
        int j=getJ(position);
        switch(direction){
            case 0://move up
                if(i==0){
                    throw new RuntimeException(position + " can't move up");
                }
                return getIndex(i-1,j);
            case 1://move down
                if(i==len-1){
                    throw new RuntimeException(position + " can't move down");
                }
                return getIndex(i+1,j);
            case 2://move left
                if(j==0){
                    throw new RuntimeException(position + " can't move left");
                }
                return getIndex(i,j-1);
            case 3://move right
                if(j==len-1){
                    throw new RuntimeException(position + " can't move right");
                }
                return getIndex(i,j+1);
            default:
                throw new RuntimeException(String.format("Direction %d is not supproted", direction));
        }
    }
}
