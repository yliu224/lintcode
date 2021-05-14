package string.lc648_m;

import java.util.*;

public class ValidWordAbbr {
    private Map<String,Integer> uniqueAbb = new HashMap<>();
    private Set<String> originalString = new HashSet<>();
    /*
    * @param dictionary: a list of words
    */public ValidWordAbbr(String[] dictionary) {
        for(int i=0;i<dictionary.length;i++){
            originalString.add(dictionary[i]);
            if(dictionary[i].length()<=2) continue;
            String abbStr = buildAbb(dictionary[i]);
            if(uniqueAbb.containsKey(abbStr)){
                uniqueAbb.put(abbStr,uniqueAbb.get(abbStr)+1);
            } else{
                uniqueAbb.put(abbStr,1);
            }
        }
    }

    private String buildAbb(String str){
        if(str.length()<=2) return str;
        int sum=0;
        for(int i=1;i<str.length();i++){
            sum++;
        }
        
        return str.charAt(0)+String.valueOf(sum)+str.charAt(str.length()-1);
    }

    /*
     * @param word: a string
     * @return: true if its abbreviation is unique or false
     * 重要的是理解题意： 1. 当 word -> abbreviation 在字典的所有abbreviation中不存在时， 
     * return true 2. 当 word 在字典中存在时, 如果没有其他字典中的词有相同的abbreviation, return true
     */
    public boolean isUnique(String word) {
        if(uniqueAbb.containsKey(buildAbb(word))){
            if(originalString.contains(word) && uniqueAbb.getOrDefault(buildAbb(word),1)==1){
                return true;
            } else{
                return false;
            }
        }
        return true;
    }
}