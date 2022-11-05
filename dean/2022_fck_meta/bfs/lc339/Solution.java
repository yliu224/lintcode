package bfs.lc339;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Solution {
    interface NestedInteger {
        Integer getInteger();
        List<NestedInteger> getList();
    }
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> q = new ArrayDeque<>();
        Queue<NestedInteger> swap = new ArrayDeque<>();
        int depth = 1;
        int sum = 0;
        q.addAll(nestedList);
        while (!q.isEmpty()) {
            while (!q.isEmpty()) {
                NestedInteger cur = q.poll();
                if (cur.getInteger() != null) {
                    sum += cur.getInteger().intValue() * depth;
                    continue;
                }
                if (!cur.getList().isEmpty()) {
                    swap.addAll(cur.getList());
                }
            }
            q.addAll(swap);
            swap.clear();
            depth++;
        }
        return sum;
    }
}
