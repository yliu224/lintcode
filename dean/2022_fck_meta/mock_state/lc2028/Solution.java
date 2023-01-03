package mock_state.lc2028;

import java.util.Arrays;

public class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int total = (rolls.length + n) * mean;
        int sum = Arrays.stream(rolls).sum();
        int diff = total - sum;
        if (diff > n * 6 || diff < n * 1) {
            return new int[0];
        }
        int[] result = new int[n];
        int divid = diff / n;
        Arrays.fill(result, divid);
        int mod = diff % n;
        for (int i = 0; i < mod; i++) {
            result[i]++;
        }
        return result;
    }
}
