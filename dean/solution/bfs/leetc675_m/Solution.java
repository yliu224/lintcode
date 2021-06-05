package bfs.leetc675_m;

import java.util.*;

public class Solution {
    private int lenI;
    private int lenJ;

    public int cutOffTree(List<List<Integer>> forest) {
        lenI = forest.size();
        lenJ = forest.get(0).size();
        int steps = 0;

        try {
            Map<Integer, Integer> mapOfTrees = getTreeMap(forest);
            int pre = -1, cur = 0;// 注意理解题意，说的是从0,0开始

            for (Map.Entry<Integer, Integer> tree : mapOfTrees.entrySet()) {
                pre = cur;
                cur = tree.getValue();
                if (pre != -1 && cur != -1) {
                    int distance = getDistance(pre, cur, forest);
                    steps = steps + distance;
                }
            }
        } catch (RuntimeException e) {
            return -1;
        }

        return steps == 0 ? -1 : steps;
    }

    private int getDistance(int start, int end, List<List<Integer>> forest) {
        if (start == end)
            return 0;
        int step = 0;
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> cacheQ = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        q.add(start);
        visited.add(start);

        while (!q.isEmpty()) {
            step++;// 注意这个step++的位置
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int next = getNextPosistion(dir, cur);
                    if (next == end) {
                        return step;
                    }
                    if (forest.get(getI(next)).get(getJ(next)) != 0 && !visited.contains(next)) {
                        cacheQ.add(next);
                        visited.add(next);
                    }
                }
            }
            q.addAll(cacheQ);
            cacheQ.clear();
        }
        throw new RuntimeException("Not reachable");
    }

    private int getIndex(int i, int j) {
        return i * lenJ + j;
    }

    private int getI(int index) {
        return index / lenJ;
    }

    private int getJ(int index) {
        return index % lenJ;
    }

    private int getNextPosistion(int direction, int cur) {
        int i = getI(cur);
        int j = getJ(cur);
        if (direction == 0 && i - 1 >= 0) {// move up
            return getIndex(i - 1, j);
        }
        if (direction == 1 && i + 1 <= lenI - 1) {// move down
            return getIndex(i + 1, j);
        }
        if (direction == 2 && j - 1 >= 0) {// move left
            return getIndex(i, j - 1);
        }
        if (direction == 3 && j + 1 <= lenJ - 1) {// move right
            return getIndex(i, j + 1);
        }
        return cur;
    }

    private Map<Integer, Integer> getTreeMap(List<List<Integer>> forest) {
        Map<Integer, Integer> mapOfTree = new TreeMap<>();

        for (int i = 0; i < lenI; i++) {
            for (int j = 0; j < lenJ; j++) {
                if (forest.get(i).get(j) > 1) {// 题中说的1是平地，所以不能加到树里面
                    mapOfTree.put(forest.get(i).get(j), getIndex(i, j));
                }
            }
        }

        return mapOfTree;
    }
}