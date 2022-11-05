package two_pointer.lc475;

import java.util.Arrays;

public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int houseIndex=0,heaterIndex=0;
        int max = -1;
        //记住双指针的模版！！！一定是一个指针动，另一个不动
        while(houseIndex<houses.length){
            while(heaterIndex<heaters.length-1){
                if(houses[houseIndex]>=heaters[heaterIndex] && houses[houseIndex]<=heaters[heaterIndex+1]){
                    break;
                }
                if(houses[houseIndex]<=heaters[heaterIndex]){
                    break;
                }
                heaterIndex++;
            }
            if(heaterIndex==heaters.length-1){
                max = Math.max(Math.abs(houses[houseIndex]-heaters[heaterIndex]),max);
            } else{
                int r1 = Math.abs(houses[houseIndex]-heaters[heaterIndex]);
                int r2 = Math.abs(houses[houseIndex]-heaters[heaterIndex+1]);
                max = Math.max(Math.min(r1,r2),max);
            }
            houseIndex++;
        }
        return max;
    }

}
