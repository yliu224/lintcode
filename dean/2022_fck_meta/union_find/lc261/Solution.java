package union_find.lc261;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private Map<Integer, Integer> set = new HashMap<>();
    private Map<Integer, Integer> setSize = new HashMap<>();

    public boolean validTree(int n, int[][] edges) {
        if (n == 1) {
            return true;
        }
        for (int[] e : edges) {
            int rootA = compressFind(e[0]);
            int rootB = compressFind(e[1]);
            if (rootA == rootB) {
                return false;
            }
            set.put(rootB, rootA);
            setSize.put(rootA, setSize.getOrDefault(rootA, 1) + setSize.getOrDefault(rootB, 1));
            setSize.remove(rootB);
        }

        if (setSize.size() != 1) {
            return false;
        } else {
            return setSize.values().iterator().next() == n;
        }
    }

    private int compressFind(int x) {
        int root = x;
        while (set.get(root) != null) {
            root = set.get(root);
        }
        while (set.get(x) != null) {
            int tmp = set.get(x);
            set.put(x, root);
            x = tmp;
        }
        return root;
    }
}
