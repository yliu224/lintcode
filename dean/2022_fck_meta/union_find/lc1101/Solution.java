package union_find.lc1101;

import java.util.*;

public class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Integer> set = new HashMap<>();

    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < logs.length; i++) {
            int rootA = compressFind(logs[i][1]);
            int rootB = compressFind(logs[i][2]);
            if (rootA != rootB) {
                map.put(rootA, rootB);
                set.put(rootB, set.getOrDefault(rootA, 1) + set.getOrDefault(rootB, 1));
                set.remove(rootA);
                if (set.get(rootB) == n) {
                    return logs[i][0];
                }
            }
        }
        return -1;
    }

    private int compressFind(int x) {
        int root = x;
        while (map.get(root) != null) {
            root = map.get(root);
        }
        while (map.get(x) != null) {
            int tmp = map.get(x);
            map.put(x, root);
            x = tmp;
        }
        return root;
    }
}
