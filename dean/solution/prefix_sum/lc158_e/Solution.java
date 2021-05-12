package prefix_sum.lc158_e;

import java.util.Arrays;

public class Solution {
    /**
     * @param s: The first string
     * @param t: The second string
     * @return: true or false
     */
    public boolean anagram(String s, String t) {
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        String newS = new String(sArray);
        String newT = new String(tArray);

        return newS.equals(newT);
    }
}