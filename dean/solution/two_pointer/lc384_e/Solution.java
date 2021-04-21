package two_pointer.lc384_e;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Character,Integer> wordCount = new HashMap<>();
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        if(s.isEmpty()) return 0;
        int maxLen = Integer.MIN_VALUE;
        int j=1;
        wordCount = add(wordCount,s.charAt(0));
        for(int i=0;i<s.length();i++){
            while(j<s.length() && isSubStrValid(wordCount)){
                maxLen = Math.max(maxLen,j-i);
                wordCount = add(wordCount,s.charAt(j));
                j++;
            }

            if(isSubStrValid(wordCount)){
                maxLen = Math.max(maxLen,j-i);
            }
            wordCount = remove(wordCount, s.charAt(i));
        }
        return maxLen;

    }  

    private Map<Character,Integer> add(Map<Character,Integer> wordCount,char c){
        if(wordCount.containsKey(c)){
            wordCount.put(c,wordCount.get(c)+1);
        } else{
            wordCount.put(c,1);
        }
        return wordCount;
    }

    private Map<Character,Integer> remove(Map<Character,Integer> wordCount,char c){
        if(wordCount.containsKey(c)){
            wordCount.put(c,wordCount.get(c)-1);
        } else{
            throw new RuntimeException(String.format("%c not found in the map", c));
        }
        return wordCount;
    }

    private boolean isSubStrValid(Map<Character,Integer> wordCount){
        for(int value:wordCount.values()){
            if(value>1){
                return false;
            }
        }
        return true;
    }
}
