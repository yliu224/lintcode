package two_pointer.lc69;

public class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        long l = 0, r = x, m;
        while (l + 1 < r) {
            m = (l + r) / 2;
            if (m * m > x) {
                r = m;
            } else {
                l = m;
            }

        }
        //System.out.println(l+":"+r);
        return (int) l;
    }
}
