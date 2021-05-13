package prefix_sum.lc774_e;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * @param s: a string represent DNA sequences
     * @return: all the 10-letter-long sequences 
     */
    public List<String> findRepeatedDna(String s) {
        if(s.length()<10) return new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<10;i++){
            sb.append(s.charAt(i));
        }
        Map<String,Integer> count = new HashMap<>();
        count.put(sb.toString(),1);
        for(int i=10;i<s.length();i++){
            sb.deleteCharAt(0);
            sb.append(s.charAt(i));
            String key = sb.toString();
            if(count.containsKey(key)){
                count.put(key,count.get(key)+1);
            } else{
                count.put(key,1);
            }
        }

        List<String> answer = new ArrayList<>();
        for(Map.Entry<String,Integer> e:count.entrySet()){
            if(e.getValue()>1){
                answer.add(e.getKey());
            }
        }
        return answer;
    }   
}
