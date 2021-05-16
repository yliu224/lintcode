package binary_search.lc438_m;

public class Solution {
    /**
     * @param n: An integer
     * @param times: an array of integers
     * @return: an integer
     */
    //dp[k][n]=k个人copy n本书所需的最短时间
    //dp[k][i]=Min(Max(dp[k-1][j],times[k]*(i-j)))
    //                        {j=1,j->i} 
    public int copyBooksII(int n, int[] times) {
        int left = 0;
        int max = 0;
        for(int i=0;i<times.length;i++){
            max = Math.max(max, times[i]);
        }

        int right = max*n;

        while(left+1<right){
            int mid = (left+right)/2;
            if(isGreater(mid,n,times)){
                right = mid;
            } else{
                left = mid;
            }
        }

        if(isGreater(left,n,times)){
            return left;
        }
        if(isGreater(right,n,times)){
            return right;
        }
        return 0;
    }

    private boolean isGreater(int minCost,int n, int[] times){
        int canFinish = 0;

        for(int i=0;i<times.length;i++){
            canFinish+=minCost/times[i];
        }

        return canFinish>=n;            
    }
}
