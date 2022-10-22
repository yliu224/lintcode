package dp.lc1191;

public class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long mod = (long) (1e9) + 7;
        if (k == 1) {
            long max1 = arr[0];
            long maxPre1 = arr[0];
            for (int i = 1; i < arr.length; i++) {
                maxPre1 = Math.max(maxPre1 + arr[i], arr[i]);
                max1 = Math.max(max1, maxPre1);
            }
            return max1 > 0 ? (int) (max1 % mod) : 0;
        }


        long[] dArray = new long[arr.length * 2];
        int len = arr.length;
        for (int i = 0; i < dArray.length; i++) {
            dArray[i] = arr[i % len];
        }

        long sum = arr[0];
        long max = arr[0];
        long maxPre = arr[0];

        for (int i = 1; i < dArray.length; i++) {
            sum += dArray[i];
            if (maxPre + dArray[i] > dArray[i]) {
                maxPre = maxPre + dArray[i];
            } else {
                maxPre = dArray[i];
            }

            if (maxPre > max) {
                max = maxPre;
            }
        }
        //仔细思考一下答案是怎么样的
        if (max > 0) {
            if (sum > 0) {
                return (int) ((max + (sum / 2) * (k - 2)) % mod);
            }
            return (int) (max % mod);
        }
        return 0;

    }
}
