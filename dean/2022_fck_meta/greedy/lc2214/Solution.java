package greedy.lc2214;

public class Solution {
    public long minimumHealth(int[] damage, int armor) {
        long max = -1;
        long sum = 1;
        for (int j : damage) {
            if (j > max) {
                max = j;
            }
            sum += j;
        }
        long leftArmors = armor-max;
        return leftArmors>0?sum-max:sum-max+Math.abs(leftArmors);
    }
}
