package dean.solution.lc391_m;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    class Interval{
      int start, end;

    Interval(int start, int end) {
          this.start = start;
          this.end = end;
      }
    }

    //Think of this case 
    //[(1,10),(10,20),(20,30),(30,40)]
    public int countOfAirplanes(List<Interval> airplanes) {
        int[] schedule = new int[airplanes.size()*2];
        Map<Integer,Integer> departure = new HashMap<>();
        Map<Integer,Integer> landing = new HashMap<>();

        int index = 0;
        for(Interval i:airplanes){
            departure.put(i.start,departure.containsKey(i.start)?departure.get(i.start)+1:1);
            schedule[index]=i.start;
            index++;
            landing.put(i.end,landing.containsKey(i.end)?landing.get(i.end)+1:1);
            schedule[index]=i.end;
            index++;
        }
        Arrays.sort(schedule);

        int inFly = 0,max = 0;

        for(int i=0;i<schedule.length;i++){
            if(landing.containsKey(schedule[i]) && landing.get(schedule[i])>0){
                landing.put(schedule[i],landing.get(schedule[i])-1);
                inFly--;
            } else if(departure.get(schedule[i])>0){
                inFly++;
                departure.put(schedule[i],departure.get(schedule[i])-1);
            }

            max = Math.max(inFly,max);
        }
        
        return max;
    }
}
