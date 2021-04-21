package two_pointer.lc386_e;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Character,Integer> wordCount = new HashMap<>();
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        if(s.isEmpty()) return 0;
        int maxLen = Integer.MIN_VALUE;
        int j=1;
        wordCount.put(s.charAt(0),1);
        for(int i=0;i<s.length();i++){
            while(j<s.length() && isValidSubStr(k)){
                maxLen = Math.max(maxLen,j-i);
                this.add(s.charAt(j));
                j++;
            }

            if(isValidSubStr(k)){
                maxLen = Math.max(maxLen,j-i);
            }
            this.delete(s.charAt(i));
        }
        return maxLen==Integer.MIN_VALUE?0:maxLen;
    }

    private void add(char c){
        if(wordCount.containsKey(c)){
            wordCount.put(c,wordCount.get(c)+1);
        } else {
            wordCount.put(c,1);
        }
    }

    private void delete(char c){
        if(wordCount.containsKey(c)){
            if(wordCount.get(c)==1){
                wordCount.remove(c);
            } else{
                wordCount.put(c,wordCount.get(c)-1);
            }
        } else{
            throw new RuntimeException(String.format("%c not found in Map", c));
        }
    }

    private boolean isValidSubStr(int k){
        if(wordCount.size()<=k){
            return true;
        }
        return false;
    }

}
