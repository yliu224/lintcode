package string.leetc1170_e;

public class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] w = new int[15];

        for (int i = 0; i < words.length; i++) {
            int count = getF(words[i]);
            w[count]++;
        }

        for (int i = w.length - 2; i >= 0; i--) {
            w[i] = w[i] + w[i + 1];
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int count = getF(queries[i]);
            if (count + 1 < w.length) {
                result[i] = w[count + 1];
            }
        }

        return result;

    }

    private int getF(String s) {
        char miniChar = 'z';

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < miniChar) {
                miniChar = s.charAt(i);
            }
        }

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == miniChar) {
                count++;
            }
        }

        return count;
    }
}
