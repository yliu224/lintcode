package string.lc639_h;

import java.util.*;

public class Solution {
    /**
     * @param dict: an array of n distinct non-empty strings
     * @return: an array of minimal possible abbreviations for every word
     */
    //不要想的太复杂
    //直接一层一层的比较，先算a#x，在算aa#x，再算aaa#x，知道算完为止
    private Map<String,Integer> dictMap = new HashMap<>();
    private StringBuilder sb = new StringBuilder();
    public String[] wordsAbbreviation(String[] dict) {
        if(dict.length==0) return new String[0];
        String[] answer = new String[dict.length];
        Map<String,Integer> index = new HashMap<>();
        List<String> toBeRemove = new ArrayList<>();

        int longestWord = 0;
        for(int i=0;i<dict.length;i++){
            index.put(dict[i],i);
            longestWord = Math.max(longestWord,dict[i].length());
        }

        for(int i=0;i<longestWord;i++){
            buildAbbMap(index, i+1);
            toBeRemove.clear();
            for(String key:index.keySet()){
                String abb = buildAbbr(key, i+1);
                if(dictMap.get(abb)==1){
                    answer[index.get(key)]=abb;
                    toBeRemove.add(key);
                }
            }
            for(String key:toBeRemove){
                index.remove(key);//需要一定的优化
            }
        }
        return answer;
    }

    private void buildAbbMap(Map<String,Integer> index, int prefixLen){
        dictMap.clear();
        for(String key:index.keySet()){
            String abb = buildAbbr(key, prefixLen);
            dictMap.put(abb,dictMap.getOrDefault(abb,0)+1);
        }
    }

    private String buildAbbr(String word,int index){
        if(word.length()<=2) return word;
        if(word.length()-2<=index) return word;
        int len = word.length()-1;
        sb.delete(0, sb.length());
        sb.append(word.substring(0,index));
        sb.append(len-index);
        sb.append(word.charAt(word.length()-1));
        return sb.toString();
    }
}
