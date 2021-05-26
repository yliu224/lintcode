package union_find.leetc827_m;

import java.util.*;

class Solution {
    private Map<Integer,Integer> set = new HashMap<>();
    private Map<Integer,Integer> setSize = new HashMap<>();
    private Set<Integer> removeDuplicate = new HashSet<>();
    private int lenI,lenJ;
    private int max = 1;
    public int largestIsland(int[][] grid) {
        lenI = grid.length;
        lenJ = grid[0].length;
        
        for(int i=0;i<lenI;i++){
            for(int j=0;j<lenJ;j++){
                if(grid[i][j]==1){
                    setSize.put(getIndex(i,j),1);
                    if(i-1>=0 && grid[i-1][j]==1){
                        merge(getIndex(i,j),getIndex(i-1,j));
                    }
                    if(j-1>=0 && grid[i][j-1]==1){
                        merge(getIndex(i,j),getIndex(i,j-1));
                    }
                }
            }
        }
        
        
        for(int i=0;i<lenI;i++){
            for(int j=0;j<lenJ;j++){
                if(grid[i][j]==0){
                    max = Math.max(fakeMerge(getIndex(i,j),grid),max);
                }
            }
        }
        
        return max;
    }
    
    private int fakeMerge(int index,int[][] grid){
        int i = getI(index);
        int j = getJ(index);
        
        int upRoot=-1,downRoot=-1,leftRoot=-1,rightRoot=-1;
        if(i-1>=0 && grid[i-1][j]==1){//move up
            upRoot = compressedFind(getIndex(i-1,j));
        }
        if(i+1<lenI && grid[i+1][j]==1){//move down
            downRoot = compressedFind(getIndex(i+1,j));
        }
        if(j-1>=0 && grid[i][j-1]==1){//move left
            leftRoot = compressedFind(getIndex(i,j-1));
        }
        if(j+1<lenJ && grid[i][j+1]==1){//move right
            rightRoot = compressedFind(getIndex(i,j+1));
        }
        removeDuplicate.clear();
        removeDuplicate.add(upRoot);
        removeDuplicate.add(downRoot);
        removeDuplicate.add(leftRoot);
        removeDuplicate.add(rightRoot);
        
        
        int size=0;
        for(int root:removeDuplicate){
            if(root!=-1){
                size+=setSize.get(root);
            }
        }
        return size+1;
    }
    
    private void merge(int x,int y){
        int rootX = compressedFind(x);
        int rootY = compressedFind(y);
        
        if(rootX!=rootY){
            set.put(rootX,rootY);
            setSize.put(rootY,setSize.getOrDefault(rootX,1)+setSize.getOrDefault(rootY,1));
            max = Math.max(setSize.get(rootY),max);
            setSize.remove(rootX);
        }
    }
    
    private int compressedFind(int x){
        int root = x;
        while(set.get(root)!=null){
            root = set.get(root);
        }
        
        while(set.get(x)!=null){
            int temp = set.get(x);
            set.put(x,root);
            x=temp;
        }
        return root;
    }
    
    private int getI(int index){
        return index/lenJ;
    }
    
    private int getJ(int index){
        return index%lenJ;
    }
    
    private int getIndex(int i,int j){
        return i*lenJ+j;
    }
}
