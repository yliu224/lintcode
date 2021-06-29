package two_pointer.leetc76_m;

import java.util.*;

public class Solution {
    private Map<Character, Integer> window;
    private Map<Character, Integer> expected;

    public String minWindow(String s, String t) {
        window = new HashMap<>();
        expected = new HashMap<>();

        initMap(t);

        int j = 0;
        int min = Integer.MAX_VALUE;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            while (!isMatch() && j < s.length()) {
                addCharacter(s.charAt(j));
                j++;// 注意这儿是先add再++
            }

            if (min > j - i && isMatch()) {
                result = s.substring(i, j);
            }
            min = Math.min(min, j - i);
            removeCharacter(s.charAt(i));
        }

        return result;
    }

    private void removeCharacter(char c) {
        if (window.containsKey(c)) {
            window.put(c, window.get(c) - 1);
        }
    }

    private void addCharacter(char c) {
        if (window.containsKey(c)) {
            window.put(c, window.get(c) + 1);
        }
    }

    private void initMap(String t) {
        for (int i = 0; i < t.length(); i++) {
            expected.put(t.charAt(i), expected.getOrDefault(t.charAt(i), 0) + 1);
            window.put(t.charAt(i), 0);
        }
    }

    private boolean isMatch() {
        for (Map.Entry<Character, Integer> e : expected.entrySet()) {
            if (e.getValue() > window.get(e.getKey())) {
                return false;
            }
        }

        return true;
    }
}
