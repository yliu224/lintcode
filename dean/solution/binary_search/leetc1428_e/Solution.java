package binary_search.leetc1428_e;

import datastructure.BinaryMatrix;

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int left =0;
        int right = binaryMatrix.dimensions().get(1)-1;
        
        while(left+1<right){
            int mid = (left+right)/2;
            
            if(isGreaterThanZero(binaryMatrix,mid)){
                right=mid;
            } else{
                left=mid;
            }
        }
        
        if(isGreaterThanZero(binaryMatrix,left)){
            return left;
        }
        if(isGreaterThanZero(binaryMatrix,right)){
            return right;
        }
        return -1;
    }
    
    private boolean isGreaterThanZero(BinaryMatrix bm,int column){
        int count = 0;
        for(int i=0;i<bm.dimensions().get(0);i++){
            if(bm.get(i,column)==1){
                count++;
            }
        }
        
        return count>=1;
    }
}
