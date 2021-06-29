package sort.leetc670_e;

import java.util.*;

public class Solution {
    public int maximumSwap(int num) {
        List<Integer> digits = new LinkedList<>();
        List<Integer> sortedDigits = new LinkedList<>();

        while (num != 0) {
            int digit = num % 10;
            digits.add(0, digit);
            sortedDigits.add(0, digit);
            num = num / 10;
        }

        Collections.sort(sortedDigits, (a, b) -> b - a);

        for (int i = 0; i < digits.size(); i++) {
            if (digits.get(i) != sortedDigits.get(i)) {
                int index = getLastDigit(sortedDigits.get(i), digits);
                int tmp = digits.get(i);
                digits.remove(i);
                digits.add(i, sortedDigits.get(i));
                digits.remove(index);
                digits.add(index, tmp);

                break;
            }
        }

        int newNum = digits.get(0);
        for (int i = 1; i < digits.size(); i++) {
            newNum = newNum * 10;
            newNum += digits.get(i);
        }

        return newNum;
    }

    private int getLastDigit(int t, List<Integer> digits) {
        for (int i = digits.size() - 1; i >= 0; i--) {
            if (digits.get(i) == t) {
                return i;
            }
        }

        throw new RuntimeException("Not found");
    }
}