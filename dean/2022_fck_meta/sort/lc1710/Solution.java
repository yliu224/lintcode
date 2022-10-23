package sort.lc1710;

import java.util.Arrays;

public class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes,(a,b)->b[1]-a[1]);
        int max = 0;
        for(int i=0;i<boxTypes.length;i++){
            if(truckSize==0){
                break;
            }
            int load = Math.min(boxTypes[i][0],truckSize);
            truckSize-=load;
            max+=load*boxTypes[i][1];
        }
        return max;
    }
}
