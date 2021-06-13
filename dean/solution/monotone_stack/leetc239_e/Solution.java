package monotone_stack.leetc239_e;

import java.util.*;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            while (!dq.isEmpty() && nums[dq.getLast()] < nums[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }
        List<Integer> result = new ArrayList<>();
        result.add(dq.getFirst());

        for (int i = k; i < nums.length; i++) {
            if (i - k == dq.getFirst()) {
                dq.removeFirst();
            }
            while (!dq.isEmpty() && nums[dq.getLast()] < nums[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
            result.add(dq.getFirst());
        }

        return result.stream().mapToInt(i -> nums[i]).toArray();
    }
}
