package black_n_red_tree.lc729;

import java.util.*;

class MyCalendar {

    TreeMap<Integer,int[]> meetings;
    public MyCalendar() {
        this.meetings = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer lowerEnd = meetings.lowerKey(start);
        Integer higherEnd = meetings.higherKey(start);
        if(lowerEnd==null){
            lowerEnd = 0;
        }
        if(higherEnd==null){
            higherEnd = Integer.MAX_VALUE;
        } else {
            higherEnd = meetings.get(higherEnd)[0];
        }
        System.out.println("s:"+lowerEnd +" -> e:"+higherEnd);
        if(start>=lowerEnd && end<=higherEnd){
            meetings.put(end,new int[]{start,end});
            return true;
        }
        return false;
    }
}
