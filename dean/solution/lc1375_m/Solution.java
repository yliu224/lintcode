package lc1375_m;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public long kDistinctCharacters(String s, int k) {
        Map<Character,Integer> characters = new HashMap<>();
        long sum = 0;
        for(int i=0;i<k;i++){
            characters = add(s.charAt(i),characters);
        }
        int j=k;
        for(int i=0;i<s.length();i++){
            while(j<s.length() && !isMatch(characters,k)){
                characters = add(s.charAt(j),characters);
                j++;
            }
            if(isMatch(characters,k)){
                sum+=s.length()-j+1;//注意这个+1的问题
                characters = remove(s.charAt(i),characters);
            }
        }
        return sum;
    }

    private Map<Character,Integer> add(char c, Map<Character,Integer> characters){
        if(characters.containsKey(c)){
            characters.put(c,characters.get(c)+1);
        } else{
            characters.put(c,1);
        }
        return characters;
    }

    private Map<Character,Integer> remove(char c, Map<Character,Integer> characters){
        if(characters.get(c)<=1){
            characters.remove(c);
        } else{
            characters.put(c,characters.get(c)-1);//注意这个初始化的问题
        }
        return characters;
    }

    private boolean isMatch(Map<Character,Integer> characters, int len){
        if(characters.size()>=len){
            return true;
        }
        return false;
    }
}
