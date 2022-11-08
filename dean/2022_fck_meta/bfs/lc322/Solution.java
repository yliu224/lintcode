package bfs.lc322;

import java.util.*;

public class Solution {
    private Set<Integer> visited = new HashSet<>();
    private Queue<Integer> q = new LinkedList<>();
    private Queue<Integer> swap = new LinkedList<>();

    public int coinChange(int[] coins, int amount) {
        int step = 0;
        q.offer(0);
        while (!q.isEmpty()) {
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur == amount) {
                    return step;
                }
                if (cur > amount) {
                    continue;
                }
                for (int c : coins) {
                    if (!visited.contains(cur + c)) {
                        visited.add(cur + c);
                        swap.offer(cur + c);
                    }
                }
            }
            q.addAll(swap);
            swap.clear();
            step++;
        }
        return -1;
    }
}
