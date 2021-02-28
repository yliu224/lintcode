package dean.solution.lc62_m;

public class Solution {
    public int search(int[] A, int target) {
        if(A.length==0) return -1;
        int left = 0;
        int right = A.length-1;

        while(left+1<right){
            int mid = (left+right)/2;

            if(isMovingLeft(A[left],A[right],A[mid],target)){
                left = mid;
            } else{
                right = mid;
            }
        }

        if(A[left]==target){
            return left;
        }
        if(A[right]==target){
            return right;
        }
        return -1;
    }

    private boolean isMovingLeft(int leftValue, int rightValue, int midValue, int target){
        if(leftValue<midValue){
            //                          L   M        R
            //this means the pattern is 5...10...0...3
            //Becareful on the match criteria !!!
            //[target>=leftValue && target<=midValue]
            if(target>=leftValue && target<=midValue){
                return false;
            }
        } else{      
            //                         L       M   R
            //this means the patter is 7...0...3...5
            //Becareful on the match criteria !!!
            //[target>=leftValue || target<=midValue]
            if(target>=leftValue || target<=midValue){
                return false;
            }
        }
        return true;
    }
}
