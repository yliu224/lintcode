package string.lc647_m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * @param s: a string
     * @param p: a string
     * @return: a list of index
     */
    private Map<Character,Integer> target = new HashMap<>();
    private Map<Character,Integer> window = new HashMap<>();
    public List<Integer> findAnagrams(String s, String p) {
        if(s.length()<p.length() || s.length()==0) return new ArrayList<>();

        List<Integer> answer = new ArrayList<>();
        for(int i=0;i<p.length();i++){
            updateTarget(p.charAt(i), 1);
            updateWindow(s.charAt(i), 1);
        }

        for(int i=0;i<s.length()-p.length();i++){
            if(isMatch()){
                answer.add(i);
            }
            updateWindow(s.charAt(i),-1);
            updateWindow(s.charAt(i+p.length()),1);
        }

        if(isMatch()){
            answer.add(s.length()-p.length());
        }
        
        return answer;
    }  

    private void updateTarget(char c,int count){
        if(target.containsKey(c)){
            target.put(c,target.get(c)+count);
        } else{
            target.put(c,1);
        }
    }

    private void updateWindow(char c,int count){
        if(window.containsKey(c)){
            window.put(c,window.get(c)+count);
            if(window.get(c)==0){
                window.remove(c);
            }
        } else{
            window.put(c,1);
        }
    }

    private boolean isMatch(){
        for(Map.Entry<Character,Integer> e:target.entrySet()){
            int win = window.getOrDefault(e.getKey(),-1);
            int tar = e.getValue();
            if(win!=tar){//注意这儿是Integer不是int，Integer不能直接==
                return false;
            }
        }
        return true;
    }
}
