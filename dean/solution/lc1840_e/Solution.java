package dean.solution.lc1840_e;

public class Solution {
    /**
     * @param n: the row of the matrix
     * @param m: the column of the matrix
     * @param after: the matrix
     * @return: restore the matrix
     */
    public int[][] matrixRestoration(int n, int m, int[][] after) {
        // write your code here
        int[][] before = new int[n][m];
        before[0][0]=after[0][0];
        for(int i=n-1;i>0;i--){
            before[i][0]=after[i][0]-after[i-1][0];
        }
        
        for(int j=m-1;j>0;j--){
            before[0][j]=after[0][j]-after[0][j-1];   
        }
        
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                before[i][j]=after[i][j]-after[i-1][j]-after[i][j-1]+after[i-1][j-1];
            }
        }
        
        return before;
    }
}