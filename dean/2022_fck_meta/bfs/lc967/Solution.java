package bfs.lc967;

import java.util.*;

public class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> swap = new LinkedList<>();
        for (int i = 1; i <= 9; i++) {
            q.offer(i);
        }

        int count = n - 1;
        while (count > 0) {
            count--;

            while (!q.isEmpty()) {
                int cur = q.poll();
                int lastDigit = cur % 10;
                if (lastDigit + k < 10 && k != 0) {
                    swap.offer(cur * 10 + lastDigit + k);
                }
                if (lastDigit - k >= 0) {
                    swap.offer(cur * 10 + lastDigit - k);
                }
            }
            q.addAll(swap);
            swap.clear();
        }

        return q.stream().mapToInt(x -> x).toArray();
    }
}
