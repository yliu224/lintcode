package string.lc49;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> ans = new HashMap<>();
        for(String s:strs){
            String encoded = encode(s);
            ans.computeIfAbsent(encoded,x->new ArrayList<>()).add(s);
        }

        return new ArrayList<>(ans.values());
    }

    private String encode(String s){
        int[] code = new int[26];
        for(char c:s.toCharArray()){
            code[c-'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i:code){
            sb.append(i).append(",");
        }

        return sb.toString();
    }
}
