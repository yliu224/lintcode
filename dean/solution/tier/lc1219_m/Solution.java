package tier.lc1219_m;

import java.util.Arrays;

public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int j=0;
        int minRadius = Integer.MIN_VALUE;
        for(int i=0;i<houses.length;i++){
            while(j<heaters.length-1 && houses[i]>=heaters[j+1]){
                j++;
            }
            int r1 = Math.abs(houses[i]-heaters[j]);
            //不一定有r2
            int r2 = Integer.MAX_VALUE;
            if(j+1<=heaters.length-1){
                r2 = Math.abs(houses[i]-heaters[j+1]);
            }

            minRadius = Math.max(minRadius,Math.min(r1,r2));
        }
        return minRadius;
    }
}
