package sort.leetc347_e;

import java.util.*;

public class Solution {
    class NumberCount {
        int number;
        int count;

        public NumberCount(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int c = counts.getOrDefault(nums[i], 0);
            c++;
            counts.put(nums[i], c);
        }

        PriorityQueue<NumberCount> pq = new PriorityQueue<>((a, b) -> b.count - a.count);

        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            NumberCount nb = new NumberCount(e.getKey(), e.getValue());
            pq.add(nb);
        }

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll().number;
        }

        return ans;
    }
}
