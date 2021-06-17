package two_pointer.leetc3_m;

import java.util.*;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.equals(""))
            return 0;
        Map<Character, Integer> count = new HashMap<>();

        int max = 1;
        int j = 0;
        count.put(s.charAt(j), 1);
        for (int i = 0; i < s.length(); i++) {
            if (j == s.length())
                break;
            while (j < s.length() - 1 && count.getOrDefault(s.charAt(j), 0) < 2) {
                j++;// 注意这儿是提前j++，不然会死循环
                count = addOne(count, s.charAt(j));// 注意把这些过程稍微抽象一下
                max = Math.max(max, count.size());
                if (count.get(s.charAt(j)) == 2) {
                    break;
                }
            }
            count = minusOrDelete(count, s.charAt(i));// 注意把这些过程稍微抽象一下
        }

        return max;
    }

    private Map<Character, Integer> minusOrDelete(Map<Character, Integer> count, Character s) {
        int c = count.get(s);
        if (c == 1) {
            count.remove(s);
        } else {
            count.put(s, c - 1);
        }
        return count;
    }

    private Map<Character, Integer> addOne(Map<Character, Integer> count, Character s) {
        count.put(s, count.getOrDefault(s, 0) + 1);
        return count;
    }
}
