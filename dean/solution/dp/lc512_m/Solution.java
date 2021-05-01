package dp.lc512_m;

public class Solution {
    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    //dp[i] 当String里面有i个字母，可以decode出的组合
    //dp[i]=validate(s[i])&&dp[i-1]+validate(s[i]s[i-1])&&dp[i-2]
    public int numDecodings(String s) {
        if(s.isEmpty() || s.startsWith("0")) return 0;
        if(s.length()==1) return 1;
        int[] dp = new int[s.length()+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=s.length();i++){
            if(isValidate(s.substring(i-1,Math.min(i,s.length())))){
                dp[i]+=dp[i-1];
            }

            if(isValidate(s.substring(i-2, Math.min(i,s.length())))){
                dp[i]+=dp[i-2];
            }
        }
        return dp[s.length()];

    }

    private boolean isValidate(String c){
        if(c.startsWith("0")) return false;
        int code = Integer.parseInt(c);
        if(code>0 && code<27){
            return true;
        }
        return false;
    }
}
