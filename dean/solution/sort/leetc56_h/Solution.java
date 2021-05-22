package sort.leetc56_h;

import java.util.*;

public class Solution {
    public int[][] merge(int[][] intervals) {
        //注意sort的用法
        Arrays.sort(intervals,(a,b)-> a[0]-b[0]);

        List<int[]> result = new ArrayList<>();

        int[] range = new int[]{intervals[0][0],intervals[0][1]};

        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]>=range[0]&&intervals[i][0]<=range[1]){
                range[1]=Math.max(range[1],intervals[i][1]);
            } else{
                result.add(Arrays.copyOf(range,2));
                range[0]=intervals[i][0];
                range[1]=intervals[i][1];
            }
        }

        result.add(Arrays.copyOf(range,2));

        int[][] answer = new int[result.size()][2];
        for(int i=0;i<result.size();i++){
            answer[i][0]=result.get(i)[0];
            answer[i][1]=result.get(i)[1];
        }

        return answer;
    }
}
