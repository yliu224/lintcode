package two_pointer.lc1405;

import java.util.*;

public class Solution {
    public String longestDiverseString(int a, int b, int c) {
        int m1 = Math.max(a, Math.max(b, c));
        int m3 = Math.min(a, Math.min(b, c));
        int m2 = (a + b + c) - (m1 + m3);
        int[] number = new int[]{a, b, c};
        char c1 = getChar(m1, number);
        char c2 = getChar(m2, number);
        char c3 = getChar(m3, number);
        List<String> s1 = new ArrayList<>();
        List<String> s2 = new ArrayList<>();
        while (m1 > 0) {
            if (m1 > 1) {
                s1.add(c1 + "" + c1);
                m1 -= 2;
            } else {
                s1.add(String.valueOf(c1));
                m1--;
            }
            if (m2 > 0) {
                s2.add(String.valueOf(c2));
                m2--;
            } else if (m3 > 0) {
                s2.add(String.valueOf(c3));
                m3--;
            }
        }
        int index = 0;
        while (m2 > 0) {
            s2.set(index, s2.get(index) + c2);
            m2--;
            index++;
        }
        index = 0;
        while (m3 > 0) {
            int i = index % s2.size();
            s2.set(i, s2.get(i) + c3);
            m3--;
            index++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s2.size(); i++) {
            sb.append(s1.get(i)).append(s2.get(i));
        }
        if (s1.size() != s2.size()) {
            sb.append(s1.get(s2.size()));
        }
        return sb.toString();
    }

    private char getChar(int m, int[] number) {
        if (m == number[0]) {
            number[0] = -1;
            return 'a';
        }
        if (m == number[1]) {
            number[1] = -1;
            return 'b';
        }
        if (m == number[2]) {
            number[2] = -1;
            return 'c';
        }
        throw new RuntimeException(m + " doesn't match");
    }
}
