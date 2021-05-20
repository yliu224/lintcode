package sort.leetc759_h;

import java.util.*;

import datastructure.Interval;

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> busyTime = new ArrayList<>();
        //注意PriorityQueue的用法
        PriorityQueue<Interval> pq = new PriorityQueue<>((a,b)->a.start-b.start);
        
        for(List<Interval> range:schedule){
            pq.addAll(range);
        }

        Interval busy = new Interval(0,0);
        for(Interval time:pq){
            if(busy.start==0 && busy.end==0){
                busy.start=time.start;
                busy.end=time.end;
            } else{
                if(time.start>=busy.start && time.start<=busy.end){
                    time.end=Math.max(busy.end,time.end);
                } else{
                    busyTime.add(busy);
                    busy = new Interval(time.start,time.end);
                }
            }
        }

        List<Interval> freeTime = new ArrayList<>();
        for(int i=1;i<busyTime.size();i++){
            if(busyTime.get(i-1).end != busyTime.get(i).start){
                freeTime.add(new Interval(busyTime.get(i-1).end,busyTime.get(i).start));
            }
        }

        return freeTime;
    }
}
