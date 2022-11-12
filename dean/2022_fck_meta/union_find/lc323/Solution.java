package union_find.lc323;

import java.util.*;

public class Solution {
    private Map<Integer, Integer> set = new HashMap<>();
    private Set<Integer> setCount = new HashSet<>();

    public int countComponents(int n, int[][] edges) {
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            visited.add(a);
            visited.add(b);
            int rootA = compressFind(a);
            int rootB = compressFind(b);
            if (rootA != rootB) {
                set.put(rootA, rootB);
                setCount.remove(rootA);
                setCount.add(rootB);
            }
        }

        return (n - visited.size()) + setCount.size();
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
