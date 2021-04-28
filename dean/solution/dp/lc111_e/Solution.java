package dp.lc111_e;

public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        int step1=1;
        int step2=2;
        int stepN=0;
        for(int i=2;i<n;i++){
            stepN=step1+step2;
            step1=step2;
            step2=stepN;
        }

        if(n==0) return 0;
        if(n==1) return step1;
        if(n==2) return step2;
        return stepN;
    }
}
