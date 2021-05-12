package prefix_sum.lc646_e;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * @param s: a string
     * @return: it's index
     */
    public int firstUniqChar(String s) {
        Map<Character,Integer> wordCount = new HashMap<>();

        for(char c:s.toCharArray()){
            if(wordCount.containsKey(c)){
                wordCount.put(c,wordCount.get(c)+1);
            } else{
                wordCount.put(c,1);
            }
            
        }

        for(int i=0;i<s.length();i++){
            if(wordCount.get(s.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }
}