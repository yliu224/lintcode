package dp.leetc91_e;

public class Solution {
    public int numDecodings(String s) {
        // dp[i] means how many ways to decode the string from [0...i]
        // dp[i]=dp[i-1]+ (26>=s.substring(i-2,i)>=1?dp[i-2]:0)
        if (s.startsWith("0") || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length() + 5];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                dp[i + 1] = dp[i];
            }
            if (Integer.parseInt(s.substring(i - 1, i + 1)) <= 26 && s.charAt(i - 1) != '0') {
                dp[i + 1] += dp[i - 1];
            }
        }
        return dp[s.length()];
    }
}
