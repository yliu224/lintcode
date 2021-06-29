package mock_state.leetc50_hh;

import java.util.*;

public class Solution {
    public double myPow(double x, int n) {
        //注意这些特殊case的处理！！！！！
        boolean positive = (n % 2 == 0 || x > 0) ? true : false;
        if (x == 1 || x == -1) {
            return positive ? 1 : -1;
        }
        if (n == -2147483648)
            return 0;

        //这儿的key是 Math.pow(2, i) ！！！
        Map<Integer, Double> powerMap = new HashMap<>();
        for (int i = 0; i <= 32; i++) {
            double p = Math.pow(2, i);
            powerMap.put((int) p, Math.pow(x, n < 0 ? -p : p));
        }

        int p = 1;
        int total = 0;
        double result = 1;
        while (total < Math.abs(n)) {
            if (result == 0.00000) {
                return 0.00000;
            }
            if (total + p > n) {
                p = 1;
            } //这里不是else，是要继续做
            result *= powerMap.get(p);
            total += p;
            p = p * 2;
        }

        return result;
    }
}
