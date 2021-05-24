package sort.leetc973_e;

import java.util.*;

public class Solution {
    public int[][] kClosest(int[][] points, int k) {

                                        // 注意这个表达式是怎么写的
        Arrays.sort(points, (x, y) -> x[0] * x[0] + x[1] * x[1] - y[0] * y[0] - y[1] * y[1]);

        int[][] answer = new int[k][2];

        for (int i = 0; i < k; i++) {
            answer[i] = points[i];
        }
        return answer;
    }
}
