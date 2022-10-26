package prefixsum.lc2256;

public class Solution {
    public int minimumAverageDifference(int[] nums) {
        long[] prefixSum = new long[nums.length + 5];

        prefixSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        long min = Long.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            long avg1 = getAvg(prefixSum, 0, i + 1);
            long avg2 = getAvg(prefixSum, i + 1, nums.length);
            if (min > Math.abs(avg1 - avg2)) {
                min = Math.abs(avg1 - avg2);
                index = i;
            }
        }

        return index;
    }

    private long getAvg(long[] prefixSum, int start, int end) {
        return (end - start) == 0 ? 0 : (prefixSum[end] - prefixSum[start]) / (end - start);
    }
}
