package union_find.lc947;

import java.util.*;

public class Solution {
    private Map<Integer, Integer> coordinateI = new HashMap<>();
    private Map<Integer, Integer> coordinateJ = new HashMap<>();
    private Map<Integer, Integer> set = new HashMap<>();
    private Map<Integer, Integer> setSize = new HashMap<>();
    private int ilen, jlen;

    public int removeStones(int[][] stones) {
        //注意这儿找ilen和jlen！！不要死记模版
        ilen = Arrays.stream(stones).mapToInt(x -> x[0]).max().getAsInt() + 1;
        jlen = Arrays.stream(stones).mapToInt(x -> x[1]).max().getAsInt() + 1;

        for (int[] s : stones) {
            int stoneI = coordinateI.getOrDefault(s[0], -1);
            int stoneJ = coordinateJ.getOrDefault(s[1], -1);
            int cur = getIndex(s[0], s[1]);
            if (stoneI != -1) {
                merge(stoneI, cur);
            }
            if (stoneJ != -1) {
                merge(stoneJ, cur);
            }
            //不要忘了加东西
            coordinateI.put(s[0], cur);
            coordinateJ.put(s[1], cur);
        }
        int count = 0;
        for (int val : setSize.values()) {
            count += val - 1;
        }
        return count;
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

    private void merge(int a, int b) {
        int rootA = compressFind(a);
        int rootB = compressFind(b);
        if (rootA != rootB) {
            set.put(rootA, rootB);
            setSize.put(rootB, setSize.getOrDefault(rootB, 1) + setSize.getOrDefault(rootA, 1));
            setSize.remove(rootA);
        }
    }

    private int getIndex(int i, int j) {
        return i * jlen + j;
    }
}
