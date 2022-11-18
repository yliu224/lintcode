package union_find.lc684;

import java.util.*;

public class Solution {
    private Map<Integer, Integer> set = new HashMap<>();

    public int[] findRedundantConnection(int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            int rootA = compressFind(edges[i][0]);
            int rootB = compressFind(edges[i][1]);

            if (rootA == rootB) {
                return edges[i];
            } else {
                set.put(rootA, rootB);
            }
        }
        return null;
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