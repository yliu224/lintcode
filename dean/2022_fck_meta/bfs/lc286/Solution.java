package bfs.lc286;

import java.util.*;

public class Solution {
    private int ilen,jlen;
    public void wallsAndGates(int[][] rooms) {
        ilen = rooms.length;
        jlen = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int i=0;i<ilen;i++){
            for(int j=0;j<jlen;j++){
                if(rooms[i][j]==0){
                    q.offer(new int[]{getIndex(i,j),0});
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur=q.poll();
            for(int dir=0;dir<4;dir++){
                int next = move(dir,cur[0],cur[1],rooms);
                if(next!=cur[0]){
                    q.offer(new int[]{next,cur[1]+1});
                    rooms[getI(next)][getJ(next)]=cur[1]+1;
                }
            }
        }
    }

    private int move(int dir,int index,int step,int[][] rooms){
        int i = getI(index);
        int j = getJ(index);
        switch(dir){
            case 0->i++;
            case 1->i--;
            case 2->j++;
            case 3->j--;
        }
        if(i<0 || j<0 || i>=ilen || j>=jlen || step+1>=rooms[i][j]){
            return index;
        }
        return getIndex(i,j);
    }

    private int getIndex(int i,int j){
        return i*jlen+j;
    }

    private int getI(int index){
        return index/jlen;
    }

    private int getJ(int index){
        return index%jlen;
    }
}
