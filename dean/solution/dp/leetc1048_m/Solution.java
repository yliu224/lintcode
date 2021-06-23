package dp.leetc1048_m;

import java.util.Arrays;

public class Solution {
    public int longestStrChain(String[] words) {
        int[] dp = new int[words.length + 5];

        Arrays.sort(words, (a, b) -> a.length() - b.length());

        for (int i = 0; i < words.length; i++) {
            dp[i] = 1;
        }

        for (int i = words.length - 1; i >= 0; i--) {
            dp[i] = getMaxDepth(i, dp, words);
        }

        int max = 0;
        for (int i = 0; i < words.length; i++) {
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public int getMaxDepth(int index, int[] dp, String[] words) {
        int max = dp[index];
        for (int i = index + 1; i < words.length; i++) {
            if (words[index].length() + 1 < words[i].length()) {
                break;
            } else if (words[index].length() + 1 == words[i].length() && canTransform(words[index], words[i])) {
                max = Math.max(max, dp[i] + 1);
            }
        }

        return max;
    }

    public boolean canTransform(String s1, String s2) {
        StringBuilder sb = new StringBuilder(s2);
        for (int i = 0; i < s2.length(); i++) {
            char c = sb.charAt(i);
            sb.deleteCharAt(i);
            if (s1.equals(sb.toString())) {
                return true;
            }
            sb.insert(i, c);
        }

        return false;
    }
}
