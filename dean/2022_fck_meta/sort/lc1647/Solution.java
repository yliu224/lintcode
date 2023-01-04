package sort.lc1647;

import java.util.*;

public class Solution {
    Map<Integer, Integer> set = new HashMap<>();

    public int minDeletions(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Integer> frequency = new ArrayList<>(map.values());
        Collections.sort(frequency);
        Set<Integer> result = new HashSet<>();
        for (int i = frequency.size() - 1; i >= 0; i--) {
            int f = frequency.get(i);
            if (!result.contains(f)) {
                result.add(f);
            } else {
                while (result.contains(f) && f != 0) {
                    f--;
                }
                if (f != 0) {
                    result.add(f);
                }
            }
        }

        return frequency.stream().mapToInt(x -> x).sum() - result.stream().mapToInt(x -> x).sum();
    }
}
