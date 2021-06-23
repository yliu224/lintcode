package mock_state.leet57_m;

import java.util.*;
import java.util.stream.Collectors;

//一定要先把主线逻辑理清楚
//先找出index
//然后再插入
public class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0)
            return new int[][] { newInterval };
        int start = newInterval[0];
        int end = newInterval[1];
        List<int[]> listInter = Arrays.stream(intervals).collect(Collectors.toList());
        listInter.add(0, new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE });
        listInter.add(listInter.size(), new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE });

        int startIndex = -1, endIndex = -1;
        for (int i = 0; i < listInter.size(); i++) {
            if (startIndex == -1) {
                if (listInter.get(i)[0] <= start && listInter.get(i)[1] >= start) {
                    startIndex = i;
                } else if (listInter.get(i)[0] >= start) {
                    startIndex = i - 1 >= 0 ? i - 1 : 0;
                }
            }

            if (endIndex == -1) {
                if (listInter.get(i)[0] <= end && listInter.get(i)[1] >= end) {
                    endIndex = i;
                } else if (listInter.get(i)[0] >= end) {
                    endIndex = i;
                }
            }
        }

        int floorStart = listInter.get(startIndex)[0];
        int floorEnd = listInter.get(startIndex)[1];
        int ceilingStart = listInter.get(endIndex)[0];
        int ceilingEnd = listInter.get(endIndex)[1];

        if (floorEnd < start && ceilingStart > end) {
            int removableIndex = startIndex + 1;
            for (int i = startIndex + 1; i < endIndex; i++) {
                listInter.remove(removableIndex);
            }
            listInter.add(startIndex + 1, newInterval);
        } else if (start >= floorStart && start <= floorEnd && end >= ceilingStart && end <= ceilingEnd) {
            int removableIndex = startIndex;
            for (int i = startIndex; i < endIndex; i++) {
                listInter.remove(removableIndex);
            }
            listInter.remove(startIndex);
            listInter.add(startIndex, new int[] { floorStart, ceilingEnd });
        } else if (floorEnd < start && (end >= ceilingStart && end <= ceilingEnd)) {
            int removableIndex = startIndex + 1;
            for (int i = startIndex + 1; i < endIndex; i++) {
                listInter.remove(removableIndex);
            }
            listInter.remove(startIndex + 1);
            listInter.add(startIndex + 1, new int[] { Math.min(start, ceilingStart), ceilingEnd });
        } else if ((floorStart <= start && floorEnd >= start) && end < ceilingStart) {
            int removableIndex = startIndex;
            for (int i = startIndex; i < endIndex; i++) {
                listInter.remove(removableIndex);
            }
            listInter.add(startIndex, new int[] { floorStart, Math.max(end, floorEnd) });
        }

        listInter.remove(0);
        listInter.remove(listInter.size() - 1);

        int[][] result = new int[listInter.size()][2];
        for (int i = 0; i < listInter.size(); i++) {
            result[i] = listInter.get(i);
        }

        return result;
    }
}
