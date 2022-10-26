package sort.lc1167;

import java.util.PriorityQueue;

public class Solution {
    public int connectSticks(int[] sticks) {
        int totalSum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < sticks.length; i++) {
            pq.add(sticks[i]);
        }

        while (pq.size() > 1) {
            Integer s1 = pq.poll();
            Integer s2 = pq.poll();
            int sum = s1 + s2;
            totalSum += sum;
            pq.add(sum);
        }
        return totalSum;
    }
}
