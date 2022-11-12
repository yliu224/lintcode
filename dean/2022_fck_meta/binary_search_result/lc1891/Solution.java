package binary_search_result.lc1891;

import java.util.Arrays;

public class Solution {
    public int maxLength(int[] ribbons, int k) {
        int l = 1, r = Arrays.stream(ribbons).max().getAsInt();
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            int cuts = getCuts(ribbons, mid);
            if (cuts >= k) {
                l = mid;
            } else {
                r = mid;
            }
        }
        int cutsL = getCuts(ribbons, l);
        int cutsR = getCuts(ribbons, r);
        if (cutsL < k) {
            return 0;
        }
        return cutsR == k ? r : l;
    }

    private int getCuts(int[] ribbons, int len) {
        int cuts = 0;
        for (int r : ribbons) {
            cuts += r / len;
        }
        return cuts;
    }
}
