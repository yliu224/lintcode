package binary_search_result.lc2064;

import java.util.Arrays;

public class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int l=0,r= Arrays.stream(quantities).max().getAsInt();
        while(l+1<r){
            int mid = (l+r)/2;
            boolean hasRemains = distribute(quantities,n,mid);
            if(hasRemains){
                l = mid;
            } else{
                r = mid;
            }
        }
        boolean lRemains = distribute(quantities,n,l);
        return lRemains==false?l:r;
    }

    private boolean distribute(int[] quantities,int n,int products){
        for(int i=0;i<quantities.length;i++){
            n-=Math.ceil(1.0*quantities[i]/products);
            if(n<0){
                return true;
            }
        }
        return false;
    }
}
