package two_pointer.lc390_e;

import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        // write your code here
        for(int i=1;i<A.length-1;i++){
            for(int j=1;j<A[i].length-1;j++){
                if(isPeak(i,j,A)){
                    return Arrays.asList(i,j);
                }
            }
        }
        return Arrays.asList();
    }

    private boolean isPeak(int i,int j,int[][] A){
        return A[i][j]>A[i-1][j] && A[i][j]>A[i+1][j] &&
           A[i][j]>A[i][j+1] && A[i][j]>A[i][j-1];
    }
}
