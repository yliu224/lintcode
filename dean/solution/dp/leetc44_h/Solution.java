package dp.leetc44_h;

import java.util.*;

public class Solution {
    public boolean isMatch(String s, String p) {
        //dp[i][j]表示p[0...i]能否匹配上s[0..j]
        //         {'z'>=p[i]>='a':dp[i-1][j-1] && s[i]==p[j]}(if '*' has presented, j:0->s.length())
        //dp[i][j]={p[i]=='*'     :dp[i-1][j] || dp[i][j-1]  }
        //         {p[i]=='?'     :dp[i-1][j-1]              }(if '*' has presented, j:0->s.length())
        if (s == null || s.length() == 0) {
            return isPMatch(p);
        }
        if (p == null || p.length() == 0) {
            return false;
        }
        boolean dp[][] = new boolean[p.length() + 5][s.length() + 5];
        boolean isStarExisted = false;
        int[] startPoint = new int[p.length() + 5];

        Arrays.fill(startPoint, -1);
        Arrays.stream(dp).forEach(a -> Arrays.fill(a, false));

        if (p.charAt(0) == '*') {
            Arrays.fill(dp[0], true);
            isStarExisted = true;
        } else if (p.charAt(0) == '?') {
            dp[0][0] = true;
        } else {
            dp[0][0] = s.charAt(0) == p.charAt(0);
        }
        //注意这儿startPoint的处理
        int start = 0;
        startPoint[0] = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                startPoint[i] = start;
                start++;
            }

        }

        for (int i = 1; i < p.length(); i++) {
            if (isStarExisted) {
                int startIndex = getStartPoint(i, startPoint);
                for (int j = startIndex; j < s.length(); j++) {
                    if (p.charAt(i) == '*') {
                        dp[i][j] = dp[i - 1][j] || (j - 1 >= 0 ? dp[i][j - 1] : false);
                    } else if (p.charAt(i) == '?') {
                        dp[i][j] = j - 1 >= 0 ? dp[i - 1][j - 1] : true;
                    } else {
                        dp[i][j] = s.charAt(j) == p.charAt(i) && (j - 1 >= 0 ? dp[i - 1][j - 1] : true);
                    }
                }
            } else {
                if (p.charAt(i) == '*') {
                    for (int j = i - 1; j < s.length(); j++) {
                        dp[i][j] = dp[i - 1][j] || (j - 1 >= 0 ? dp[i][j - 1] : false);
                    }
                    isStarExisted = true;
                } else if (p.charAt(i) == '?' && i < s.length()) {
                    dp[i][i] = dp[i - 1][i - 1];
                } else if (i < s.length()) {
                    dp[i][i] = s.charAt(i) == p.charAt(i) && dp[i - 1][i - 1];
                }
            }
        }

        return dp[p.length() - 1][s.length() - 1];
    }

    private int getStartPoint(int i, int[] p) {
        while (i >= 0) {
            if (p[i] != -1) {
                return p[i];
            }
            i--;
        }
        return p[0];
    }

    private boolean isPMatch(String p) {
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }

        return true;
    }
}
