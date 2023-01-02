package two_pointer.lc984;

import java.util.*;

public class Solution {
    public String strWithout3a3b(int a, int b) {
        List<String> longStr = new ArrayList<>();
        List<String> shortStr = new ArrayList<>();
        char longChar = a > b ? 'a' : 'b';
        char shortChar = a > b ? 'b' : 'a';
        int longLen = Math.max(a, b);
        int shortLen = Math.min(a, b);

        while (longLen > 0) {
            if (longLen > 1) {
                longStr.add(longChar + "" + longChar);
                longLen -= 2;
            } else {
                longStr.add(String.valueOf(longChar));
                longLen -= 1;
            }
            if (shortLen > 0) {
                shortStr.add(String.valueOf(shortChar));
                shortLen--;
            }
        }
        //System.out.println(longLen+":"+shortLen);
        int index = 0;
        while (shortLen > 0) {
            shortStr.set(index++, shortChar + "" + shortChar);
            shortLen--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shortStr.size(); i++) {
            sb.append(longStr.get(i)).append(shortStr.get(i));
        }
        if (shortStr.size() != longStr.size()) {
            sb.append(longStr.get(longStr.size() - 1));
        }
        return sb.toString();
    }
}
