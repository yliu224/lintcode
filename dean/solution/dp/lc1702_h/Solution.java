package dp.lc1702_h;

public class Solution {
    private int MOD=1000000007;
    /**
     * @param S: The string s
     * @return: The number of distinct, non-empty subsequences of S.
     */
     //dp[i]表示第i个字母，能与前面所有字母能组成的distinct sequence的数量
     //for(j=i;j->0){
     //     dp[i]+=dp[j]
     //     if(S[i]==S[j]) break;  
     //}
     //if(j==0) dp[i]++;
     //return sum(dp[0]..dp[S.length()])
    public int distinctSubseqII(String S) {
        long[] dp = new long[S.length()];

        dp[0]=1;
        for(int i=1;i<S.length();i++){
            int j=i-1;
            for(;j>=0;j--){
                dp[i]=(dp[i]+dp[j])%MOD;
                if(S.charAt(i)==S.charAt(j)) break;
            }
            if(j==-1) dp[i]=(dp[i]+1)%MOD;
        }

        int sum = 0;
        for(int i=0;i<S.length();i++){
            sum=(sum+(int)dp[i])%MOD;
        }

        return sum;
    }
}
