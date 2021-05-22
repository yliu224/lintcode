package union_find.leetc200_m;

import java.util.*;

public class Solution {
    private Map<Integer, Integer> set = new HashMap<>();
    private Map<Integer, Integer> setSize = new HashMap<>();
    private int lenI;
    private int lenJ;

    public int numIslands(char[][] grid) {
        lenI = grid.length;
        lenJ = grid[0].length;
        for (int i = 0; i < lenI; i++) {
            for (int j = 0; j < lenJ; j++) {
                if (grid[i][j] == '1') {
                    setSize.put(getIndex(i, j), 1);// 这个地方注意一下
                    for (int step = 0; step < 2; step++) {
                        mergeCell(i, j, step, grid);
                    }
                }
            }
        }
        return setSize.size();
    }

    private int getIndex(int i, int j) {
        return i * lenJ + j;
    }

    private void mergeCell(int i, int j, int step, char[][] grid) {
        if (step == 0 && i - 1 >= 0 && grid[i - 1][j] == '1') {
            merge(getIndex(i, j), getIndex(i - 1, j));
            return;
        }
        if (step == 1 && j - 1 >= 0 && grid[i][j - 1] == '1') {
            merge(getIndex(i, j), getIndex(i, j - 1));
            return;
        }

    }

    private void merge(int x, int y) {
        int rootX = compressed_find(x);
        int rootY = compressed_find(y);

        if (rootX != rootY) {
            set.put(rootX, rootY);
            setSize.put(rootY, setSize.getOrDefault(rootX, 1) + setSize.getOrDefault(rootY, 1));
            setSize.remove(rootX);
        }
    }

    private int compressed_find(int x) {
        int root = x;

        while (set.get(root) != null) {
            root = set.get(root);
        }

        while (set.get(x) != null) {
            int temp = set.get(x);
            set.put(x, root);
            x = temp;
        }

        return root;
    }

}
