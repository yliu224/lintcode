package prefix_sum.lc772_m;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * @param strs: the given array of strings
     * @return: The anagrams which have been divided into groups
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> group = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            char[] charS = strs[i].toCharArray();
            Arrays.sort(charS);
            String newS = new String(charS);
            if(!group.containsKey(newS)){
                group.put(newS,new ArrayList<>(Arrays.asList(strs[i])));
            } else{
                group.get(newS).add(strs[i]);
            }
        }

        List<List<String>> answer = new ArrayList<>();
        for(Map.Entry<String,List<String>> e:group.entrySet()){
            answer.add(e.getValue());
        }

        return answer;
    }
}