package dp.leetc72_h;

public class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j]表示word1[0...i]变成word2[0...j]需要的最少步数
        // dp[i][j]=Math.min(dp[i-1][j]+1,dp[i][j-1]+1,word1[i]==word2[j]?dp[i-1][j-1]:dp[i-1][j-1]+1
        // 从hr<-->ro可以是
        // ro--->r(1)-->hr(3)删除o在加h。这个就是dp[i-1][j]
        // ro--->h(2)-->hr(3)ro变成h要2步再加r。这个是dp[i][j-1]
        // r---->h(1)-->hr(2)r变成h为1再加r。这个是dp[i-1][j-1]
        //   # h r s e
        // # 0 1 2 3 4
        // r 1 1 1 2 3
        // o 2 2 2 2 3
        // s 3 3 3 2 3
        int[][] dp = new int[word1.length() + 5][word2.length() + 5];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                int addOrDelete = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], addOrDelete);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, addOrDelete);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
