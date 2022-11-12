package sort.lc2279;

import java.util.PriorityQueue;

public class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->(a[0]-a[1])-(b[0]-b[1]));
        int fullBags = 0;
        for(int i=0;i<capacity.length;i++){
            if(capacity[i]==rocks[i]){
                fullBags++;
            } else{
                pq.add(new int[]{capacity[i],rocks[i]});
            }
        }

        while(!pq.isEmpty() && additionalRocks>0){
            int[] cur = pq.poll();
            additionalRocks-=cur[0]-cur[1];
            if(additionalRocks>=0){
                fullBags++;
            }
        }
        return fullBags;
    }
}
