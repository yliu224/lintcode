package union_find.lc261_h;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    /**
     * @param matrix: the matrix for calculation.
     * @return: return the max area after operation at most once.
     */
    private Map<Integer,Integer> union = new HashMap<>();
    private Map<Integer,Integer> size = new HashMap<>();
    private int len;

    public int maxArea(int[][] matrix) {
        len = matrix[0].length;
        buildUnion(matrix);

        return getMaxConnect(matrix);
    }

    private int getMaxConnect(int[][] matrix){
        int max = Integer.MIN_VALUE;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]==0){//什么时候做Union
                    max = Math.max(tryUnionNeighbors(i,j,matrix),max);
                }
            }
        }
        return max;
    }

    private void buildUnion(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]==1){//什么时候做Union
                    size.put(getIndex(i,j),1);//记得初始化单个Union
                    if(canAddToUnion(i+1, j, matrix)){
                        union(getIndex(i+1,j),getIndex(i, j));
                    }
                    if(canAddToUnion(i-1, j, matrix)){
                        union(getIndex(i-1,j),getIndex(i, j));
                    }
                    if(canAddToUnion(i, j+1, matrix)){
                        union(getIndex(i,j+1),getIndex(i, j));
                    }
                    if(canAddToUnion(i, j-1, matrix)){
                        union(getIndex(i,j-1),getIndex(i, j));
                    }
                }
            }
        }
    }

    private int getIndex(int i,int j){
        return i*len+j;
    }

    private boolean canAddToUnion(int i,int j,int[][] matrix){
        if( i<0 || i>matrix.length-1 || 
            j<0 || j>matrix[0].length-1){
            return false;
        }
        
        return matrix[i][j]==1;
    }

    private int tryUnionNeighbors(int i,int j,int[][] matrix){
        Set<Integer> roots = new HashSet<>();

        if(canAddToUnion(i+1, j, matrix)){
            roots.add(compressed_find(getIndex(i+1,j)));
        }

        if(canAddToUnion(i-1, j, matrix)){
            roots.add(compressed_find(getIndex(i-1,j)));
        }

        if(canAddToUnion(i, j+1, matrix)){
            roots.add(compressed_find(getIndex(i,j+1)));
        }

        if(canAddToUnion(i, j-1, matrix)){
            roots.add(compressed_find(getIndex(i,j-1)));
        }
        
        int answer = 1;
        for(Integer r:roots){
            answer+=size.get(r)==null?1:size.get(r);
        }  
        return answer;
    }

    private void union(int a, int b){
        int roota = compressed_find(a);
        int rootb = compressed_find(b);

        if(roota!=rootb){
            union.put(roota,rootb);
        }
        
        int sizea = size.get(roota) == null ? 1:size.get(roota);
        int sizeb = size.get(rootb) == null ? 1:size.get(rootb);

        size.put(rootb, sizea+sizeb);
        size.put(roota, 0);
    }

    private int compressed_find(int x){
        int root = x;
        while(union.get(root)!=null){
            root = union.get(root);
        }

        while(root!=x){
            int parent = union.get(x);
            union.put(x,root);
            x = parent;
        }

        return root;
    }
}
