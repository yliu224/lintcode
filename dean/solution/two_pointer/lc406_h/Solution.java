package dean.solution.two_pointer.lc406_h;

public class Solution {
    public int subarraySumII(int[] A, int start, int end) {
        // write your code here
        int[] prefixSum = getPrefixSum(A);
        //思考如何利用题里面的条件，所有数都是正数
        int startLeft=1,endRight=1;
        int sum = 0;
        for(int i=0;i<A.length;i++){
            while(startLeft<prefixSum.length && prefixSum[startLeft]-prefixSum[i]<start){
                startLeft++;
            }

            while(endRight<prefixSum.length && prefixSum[endRight]-prefixSum[i]<=end){
                endRight++;
            }
            //注意这个特殊情况
            if(startLeft==endRight){
                startLeft++;
                endRight++;
            }
            sum+=endRight-startLeft;
        }

        return sum;
    }

    private int[] getPrefixSum(int[] A){
        int[] prefixSum = new int[A.length+1];
        prefixSum[0]=0;
        for(int i=0;i<A.length;i++){
            prefixSum[i+1]=A[i]+prefixSum[i];
        }

        return prefixSum;
    }
}
