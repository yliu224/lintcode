package dean.solution.lc32_h;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String minWindow(String source , String target) {
        if(target.length()>source.length() || source.isEmpty() || target.isEmpty()){
            return "";
        }
        // write your code here
        HashMap<Character,Integer> sourceMap = new HashMap<>();
        HashMap<Character,Integer> targetMap = new HashMap<>();

        for(char c : target.toCharArray()){
            targetMap.put(c,targetMap.get(c)==null?1:targetMap.get(c)+1);
        }
        
        int matchedChar = 0;
        int expectedMatchedChar = targetMap.size();
        int j =0;
        String minSubStr = "";
        sourceMap.put(source.charAt(j),1);
        matchedChar +=getMatchedCharVariation(sourceMap, targetMap, source.charAt(j),true);

        for(int i = 0;i<source.length();i++){
            //[WARN] source.length()-1 or get IndexError
            while(j<source.length()-1 && expectedMatchedChar != matchedChar){
                j++;
                char c = source.charAt(j);
                sourceMap.put(c, sourceMap.get(c)==null?1:sourceMap.get(c)+1);
                matchedChar += getMatchedCharVariation(sourceMap,targetMap,source.charAt(j),true);
            }

            if(expectedMatchedChar == matchedChar){
                //[WARN] Be restrict on the crieteria 
                //substring(i,j) will not take the end index char
                minSubStr = (minSubStr.length()>j-i+1 || minSubStr.isEmpty())?source.substring(i, j+1):minSubStr;
            }

            sourceMap.put(source.charAt(i),sourceMap.get(source.charAt(i))-1);
            //[WARN] Be careful on the index. It should be "source.charAt(i)"" NOT "source.charAt(j)""
            matchedChar += getMatchedCharVariation(sourceMap,targetMap,source.charAt(i),false);
        }

        return minSubStr;
    }

    public int getMatchedCharVariation(Map<Character,Integer> sourceMap,Map<Character,Integer> targetMap,char c,boolean isAdd){
        if(targetMap.containsKey(c)){
            //[WARN] PLEASE use intValue()!!!
            if(sourceMap.get(c).intValue()==targetMap.get(c).intValue()-1 && !isAdd){
                return -1;
            }
            //[WARN] PLEASE use intValue()!!!
            if(sourceMap.get(c).intValue() == targetMap.get(c).intValue() && isAdd){
                return 1;
            }
        }
        return 0;
    }
}