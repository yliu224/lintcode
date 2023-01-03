package string.lc1578;

public class Solution {
    public int minCost(String colors, int[] neededTime) {
        int curColor = 0;
        colors = colors.concat("0");
        int sum = 0;
        for (int i = 1; i < colors.length(); i++) {
            if (colors.charAt(i) != colors.charAt(curColor)) {
                sum += getSum(neededTime, curColor, i - 1);
                curColor = i;
            }
        }
        return sum;
    }

    private int getSum(int[] neededTime, int start, int end) {
        int sum = 0, max = 0;
        for (int i = start; i <= end; i++) {
            sum += neededTime[i];
            max = Math.max(max, neededTime[i]);
        }
        return sum - max;
    }
}
