package two_pointer.leetc1539_e;

public class Solution {
    public int findKthPositive(int[] arr, int k) {
        boolean[] state = new boolean[2005];

        for (int i = 0; i < arr.length; i++) {
            state[arr[i]] = true;
        }

        int missingCount = 0;

        for (int i = 1; i < 2005; i++) {
            if (state[i] == false) {
                missingCount++;
            }
            if (missingCount == k) {
                return i;
            }
        }
        return -1;
    }
}