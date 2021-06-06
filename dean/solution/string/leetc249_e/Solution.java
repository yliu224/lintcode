package string.leetc249_e;

import java.util.*;

public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, Integer> strs = new HashMap<>();

        for (String s : strings) {
            strs.put(s, strs.getOrDefault(s, 0) + 1);
        }

        List<List<String>> result = new ArrayList<>();

        while (!strs.isEmpty()) {
            String start = strs.keySet().stream().findAny().get();
            List<String> group = new ArrayList<>();

            group = add(start, strs.get(start), group);
            strs.remove(start);

            String next = getNext(start);
            while (!next.equals(start)) {
                if (strs.containsKey(next)) {
                    group = add(next, strs.get(next), group);
                    strs.remove(next);
                }
                next = getNext(next);
            }
            result.add(group);
        }

        return result;
    }

    private String getNext(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((char) (c + 1) <= 'z') {
                sb.append((char) (c + 1));
            } else {
                sb.append('a');
            }
        }
        return sb.toString();
    }

    private List<String> add(String s, int count, List<String> group) {
        for (int i = 0; i < count; i++) {
            group.add(s);
        }
        return group;
    }
}
