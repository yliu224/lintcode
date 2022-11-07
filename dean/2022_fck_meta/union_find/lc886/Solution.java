package union_find.lc886;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Map<Integer, Integer> set = new HashMap<>();

    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        //先构建双向图
        for (int[] d : dislikes) {
            map.computeIfAbsent(d[0], x -> new ArrayList<>()).add(d[1]);
            map.computeIfAbsent(d[1], x -> new ArrayList<>()).add(d[0]);
        }

        for (int i = 1; i <= n; i++) {
            int rootA = compressFind(i);
            if (map.containsKey(i) && map.get(i).size() != 0) {
                List<Integer> noLikes = map.get(i);
                //找到对家
                int rootB = compressFind(map.get(i).get(0));
                for (int j = 0; j < noLikes.size(); j++) {
                    int rootC = compressFind(noLikes.get(j));
                    if (rootA == rootC) {
                        return false;
                    }
                    merge(rootB, rootC);
                }
            }
        }
        return true;
    }

    private void merge(int rootB, int rootC) {
        if (rootB != rootC) {
            set.put(rootB, rootC);
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
