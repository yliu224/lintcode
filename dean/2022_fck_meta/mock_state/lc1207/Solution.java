package mock_state.lc1207;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    private Map<Integer, Integer> count = new HashMap<>();

    public boolean uniqueOccurrences(int[] arr) {
        for (int n : arr) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        Set<Integer> s = new HashSet<>();
        for (int n : count.values()) {
            if (s.contains(n)) {
                return false;
            }
            s.add(n);
        }

        return true;
    }
}
