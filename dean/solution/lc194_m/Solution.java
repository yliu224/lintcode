package lc194_m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


//Learn what is TreeSet !!!
public class Solution {
    public List<String> findWords(String str, List<String> dict) {
        // Write your code here.
        Map<Character, TreeSet<Integer>> strMap = new HashMap<>();

        for(int i=0;i<str.length();i++){
            if(strMap.containsKey(str.charAt(i))){
                strMap.get(str.charAt(i)).add(i);
            } else{
                TreeSet<Integer> indexSet = new TreeSet<>();
                indexSet.add(i);
                strMap.put(str.charAt(i),indexSet);
            }
        }

        List<String> answer = new ArrayList<>();
        for(String s:dict){
            if(isFound(s,strMap)){
                answer.add(s);
            }
        }

        return answer;
    }

    private boolean isFound(String s, Map<Character, TreeSet<Integer>> strMap) {
        Integer preIndex=-1;
        for(char c:s.toCharArray()){
            if(strMap.containsKey(c)){
                preIndex = strMap.get(c).higher(preIndex);
                if(preIndex==null){
                    return false;
                }
            } else{
                return false;
            }
        }

        return true;
    }
}