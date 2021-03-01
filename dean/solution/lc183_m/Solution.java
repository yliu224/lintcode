package dean.solution.lc183_m;

public class Solution {
    public int woodCut(int[] L, int k) {
        if(L.length==0) return 0;
        // write your code here
        long left=1,right=Integer.MIN_VALUE;
        //这里是找 L[] 里面的MAX
        for(int l:L){
            right = Math.max(l,right);
        }

        while(left+1<right){
            //这里不能是left/2+right/2
            long mid = (left+right)/2;

            if(canCutInto(mid,L,k)){
                left = mid;
            }else{
                right = mid;
            }
        }

        //Right在前！
        if(canCutInto(right,L,k)){
            return (int)right;
        }
        if(canCutInto(left,L,k)){
            return (int)left;
        }
        return 0;
    }

    private boolean canCutInto(long n,int[] L, int target){
        int numberOfPiecies = 0;
        for(int l:L){
            numberOfPiecies+=l/n;
        }
        //用模板的时候，一定要思考==的情况！！
        return numberOfPiecies>=target;
    }
}
