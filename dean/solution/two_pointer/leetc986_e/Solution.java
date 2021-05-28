package two_pointer.leetc986_e;

import java.util.*;

class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList.length == 0 || secondList.length == 0)
            return new int[0][0];

        List<int[]> intervals = new ArrayList<>();

        int first = 0, second = 0;

        while (first < firstList.length - 1 || second < secondList.length - 1) {
            int[] interval = getInterval(firstList[first], secondList[second]);

            if (interval != null) {
                intervals.add(interval);
            }

            if (moveFirst(firstList[first], secondList[second])) {
                if (first < firstList.length - 1) {
                    first++;
                } else {
                    second++;
                }
            } else {
                if (second < secondList.length - 1) {
                    second++;
                } else {
                    first++;
                }
            }
        }

        int[] interval = getInterval(firstList[first], secondList[second]);

        if (interval != null) {
            intervals.add(interval);
        }

        int[][] answer = new int[intervals.size()][2];

        for (int i = 0; i < intervals.size(); i++) {
            answer[i] = intervals.get(i);
        }
        return answer;
    }

    private int[] getInterval(int[] first, int[] second) {
        int start = 0, end = 0;
        if (first[1] < second[0] || second[1] < first[0]) {
            return null;
        }
        if (first[0] >= second[0]) {
            start = first[0];
        } else {
            start = second[0];
        }

        if (first[1] <= second[1]) {
            end = first[1];
        } else {
            end = second[1];
        }

        return new int[] { start, end };
    }

    private boolean moveFirst(int[] first, int[] second) {
        if (first[1] <= second[1]) {
            return true;
        }
        return false;
    }
}
