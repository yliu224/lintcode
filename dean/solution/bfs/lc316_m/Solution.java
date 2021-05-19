package bfs.lc316_m;

import java.util.*;

//内存优化，极限case
//[0,1,2,3,4,5,6,7,8]
//1000000
public class Solution {
    /**
     * @param num:    a array
     * @param target: a num
     * @return: return all combinations
     */
    public List<Integer> combinationSet(List<Integer> num, int target) {
        List<Integer> q = new ArrayList<>();

        for (int i = 0; i < num.size(); i++) {
            if (num.get(i) < target) {
                q.add(num.get(i));
            }

        }

        int index = 0;
        while (index < q.size()) {
            int cur = q.get(index);
            index++;
            if (cur == 0) {
                continue;
            }

            for (int i = 0; i < num.size(); i++) {
                int next = cur * 10 + num.get(i);
                if (next < target) {
                    q.add(next);
                }
            }
        }

        return q;
    }
}
