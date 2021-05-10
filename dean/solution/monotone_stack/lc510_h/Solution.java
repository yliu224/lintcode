package monotone_stack.lc510_h;

import java.util.Stack;

//和122类似，把这个map一排一排的找max area
public class Solution {
    /**
     * @param matrix: a boolean 2D matrix
     * @return: an integer
     */
    public int maximalRectangle(boolean[][] matrix) {
        if(matrix.length==0) return 0;
        int[][] heightsMap = buildHeightsMap(matrix);

        int max = 0;
        for(int i=0;i<heightsMap.length;i++){
            Stack<Integer> stack = new Stack<>();
            for(int j=0;j<=heightsMap[i].length;j++){
                int value = j==heightsMap[i].length?-1:heightsMap[i][j];
                while(!stack.isEmpty() && !isValid(heightsMap[i][stack.peek()],value)){
                    int cur = stack.pop();
                    int left = stack.isEmpty()?-1:stack.peek();
                    int width = j-left-1;
                    max = Math.max(max,heightsMap[i][cur]*width);
                }
                stack.push(j);
            }
        }

        return max;
    }    

    private boolean isValid(int peek,int height){
        return peek<=height;
    }

    private int[][] buildHeightsMap(boolean[][] matrix){
        int[][] heightsMap = new int[matrix.length][matrix[0].length];

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(i==0){
                    if(matrix[i][j]){
                        heightsMap[i][j]=1;
                    }
                } else{
                    if(matrix[i][j]){
                        heightsMap[i][j]=heightsMap[i-1][j]+1;
                    }
                }

            }
        }
        return heightsMap;
    }
}
