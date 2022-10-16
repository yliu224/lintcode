package two_pointer.lc170;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    private Map<Integer, Integer> numbers = new HashMap<>();
    private List<Integer> lnumbers = new ArrayList<>();

    public Solution() {

    }

    public void add(int number) {
        int count = numbers.getOrDefault(number, 0);
        numbers.put(number, count + 1);
        lnumbers.add(number);
    }

    public boolean find(int value) {
        for (int n : lnumbers) {
            int left = value - n;
            numbers.put(n, numbers.get(n) - 1);

            if (numbers.get(left) != null && numbers.get(left) > 0) {
                numbers.put(n, numbers.get(n) + 1);
                return true;
            }

            numbers.put(n, numbers.get(n) + 1);
        }
        return false;
    }
}
