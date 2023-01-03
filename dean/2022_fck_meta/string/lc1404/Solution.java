package string.lc1404;

public class Solution {
    public int numSteps(String s) {
        boolean carry = false;
        int steps = 0;
        for (int i = s.toCharArray().length - 1; i >= 0; i--) {
            if (carry) {
                if (s.charAt(i) == '0') {
                    steps++;
                }
            } else {
                if (s.charAt(i) == '1' && i != 0) {
                    carry = true;
                    steps++;
                }
            }
            steps++;
        }
        return steps - (carry ? 0 : 1);
    }
}
