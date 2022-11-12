package two_pointer.lc1011;

import java.util.Arrays;

public class Solution {
    //这道题是二分答案！！经典二分题
    public int shipWithinDays(int[] weights, int days) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int neededDays = getDays(weights, mid);
            if (neededDays > days) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (getDays(weights, left) <= days) {
            return left;
        }
        return right;
    }

    private int getDays(int[] weights, int cap) {
        int days = 0;
        int ship = 0;
        for (int w : weights) {
            if (ship + w > cap) {
                ship = 0;
                days++;
            }
            ship += w;
        }
        return days + 1;
    }
}
