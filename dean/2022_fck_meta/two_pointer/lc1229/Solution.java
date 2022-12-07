package two_pointer.lc1229;

import java.util.*;

public class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int s1 = 0, s2 = 0;
        while (s1 < slots1.length && s2 < slots2.length) {
            int start = Math.max(slots1[s1][0], slots2[s2][0]);
            int end = Math.min(slots1[s1][1], slots2[s2][1]);
            if (end - start >= duration) {
                return List.of(start, start + duration);
            }
            if (slots1[s1][1] > slots2[s2][1]) {
                s2++;
            } else {
                s1++;
            }
        }
        return List.of();
    }
}
