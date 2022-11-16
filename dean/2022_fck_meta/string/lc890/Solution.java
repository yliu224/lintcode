package string.lc890;

import java.util.*;

public class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        Map<String, List<String>> patterns = new HashMap<>();
        for (String s : words) {
            patterns.computeIfAbsent(generatePattern(s), x -> new ArrayList<>()).add(s);
        }

        return patterns.getOrDefault(generatePattern(pattern), new ArrayList<>());
    }

    private String generatePattern(String s) {
        Map<Character, Character> p = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < s.toCharArray().length; i++) {
            if (!p.containsKey(s.charAt(i))) {
                p.put(s.charAt(i), (char) ('a' + index++));
            }
            sb.append(p.get(s.charAt(i)));
        }
        return sb.toString();
    }
}
