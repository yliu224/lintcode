package union_find.lc434_m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datastructure.Point;

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    private Map<Integer,Integer> set = new HashMap<>();
    private Map<Integer,Integer> setSize = new HashMap<>();
    private int lenI;
    private int lenJ;
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        if(operators==null) return new ArrayList<Integer>();
        lenI=n;
        lenJ=m;
        int[][] map = new int[n][m];
        List<Integer> result = new ArrayList<>();
        for(Point p:operators){
            map[p.x][p.y]=1;
            setSize.put(getIndex(p.x,p.y),1);
            mergeNeighbors(p,map);
            result.add(getIslandCount());
        }
        return result;
    }

    private int getIslandCount(){
        return setSize.keySet().size();
    }

    private void mergeNeighbors(Point p,int[][] map){
        if(p.x-1>=0&&map[p.x-1][p.y]==1){
            merge(getIndex(p.x,p.y), getIndex(p.x-1, p.y));
        }
        if(p.y-1>=0&&map[p.x][p.y-1]==1){
            merge(getIndex(p.x,p.y), getIndex(p.x, p.y-1));            
        }
        if(p.x+1<=lenI-1&&map[p.x+1][p.y]==1){
            merge(getIndex(p.x,p.y), getIndex(p.x+1, p.y));            
        }
        if(p.y+1<=lenJ-1&&map[p.x][p.y+1]==1){
            merge(getIndex(p.x,p.y), getIndex(p.x, p.y+1));            
        }
    }

    private void merge(int x,int y){
        int rootX = compressed_find(x);
        int rootY = compressed_find(y);

        if(rootX!=rootY){
            set.put(rootX,rootY);
            setSize.put(rootY,setSize.getOrDefault(rootX,1)+setSize.getOrDefault(rootY,1));
            setSize.remove(rootX);
        }
    }

    private int compressed_find(int x){
        int root = x;
        while(set.get(root)!=null){
            root = set.get(root);
        }

        while(set.get(x)!=null){
            int tmp = set.get(x);
            set.put(x,root);
            x = tmp;
        }

        return root;
    }

    private int getIndex(int i,int j){
        return i*lenJ+j;
    }
}
