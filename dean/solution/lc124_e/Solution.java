package lc124_e;

import java.util.Arrays;

public class Solution {
    /**
     * @param num: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] num) {
        Arrays.sort(num);
        int maxlen=1;
        int len=1;
        for(int i=1;i<num.length;i++){
            if(num[i]-num[i-1]==1){
                len++;
                maxlen=Math.max(maxlen,len);
            } else if(num[i]==num[i-1]){
                continue;
            } else {
                len=1;
            }
        }

        return maxlen;
    }
}
