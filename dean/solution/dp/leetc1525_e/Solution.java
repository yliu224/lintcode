package dp.leetc1525_e;

import java.util.*;

public class Solution {
    public int numSplits(String s) {
        int[] fromLeft = new int[s.length()];
        int[] fromRight = new int[s.length()];
        Set<Character> count = new HashSet<>();

        fromLeft[0] = 1;
        count.add(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            if (!count.contains(s.charAt(i))) {
                fromLeft[i] = fromLeft[i - 1] + 1;
                count.add(s.charAt(i));
            } else {
                fromLeft[i] = fromLeft[i - 1];
            }
        }

        fromRight[s.length() - 1] = 1;
        count.clear();
        count.add(s.charAt(s.length() - 1));

        for (int i = s.length() - 2; i >= 0; i--) {
            if (!count.contains(s.charAt(i))) {
                fromRight[i] = fromRight[i + 1] + 1;
                count.add(s.charAt(i));
            } else {
                fromRight[i] = fromRight[i + 1];
            }
        }

        int matched = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            if (fromLeft[i] == fromRight[i + 1]) {
                matched++;
            }
        }

        return matched;
    }
}