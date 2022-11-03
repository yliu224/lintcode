package sort.lc253;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        int rooms = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            while (!pq.isEmpty()) {
                int[] cur = pq.peek();
                if(cur[1]<=intervals[i][0]){
                    pq.poll();
                } else{
                    //注意这儿要break
                    break;
                }
            }
            pq.add(intervals[i]);
            rooms = Math.max(rooms,pq.size());
        }

        return rooms;
    }
}
