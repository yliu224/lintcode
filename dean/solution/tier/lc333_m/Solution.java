package tier.lc333_m;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    class TrieNode {
        public Map<Character,TrieNode> children;
        public boolean isWord;
        public String word;
        public int wordCount;

        public TrieNode(String prefix){
            children = new HashMap<>();
            isWord = false;
            word = prefix;
            wordCount = 0;
        }
    }
    /**
     * @param stringArray: a string array
     * @return: return every strings'short peifix
     */
    public String[] ShortPerfix(String[] stringArray) {
        TrieNode root = new TrieNode("");
        
        for(int i=0;i<stringArray.length;i++){
            TrieNode node = root;
            for(char c:stringArray[i].toCharArray()){
                if(!node.children.containsKey(c)){
                    node.children.put(c,new TrieNode(node.word+String.valueOf(c)));
                }
                node=node.children.get(c);
                node.wordCount++;
            }
            node.isWord = true;
        }

        String[] prefixes = new String[stringArray.length];
        for(int i=0;i<stringArray.length;i++){
            TrieNode node = root;
            for(char c:stringArray[i].toCharArray()){
                node = node.children.get(c);
                if(node.wordCount==1){
                    prefixes[i]=node.word;
                    break;
                }
            }
            if(prefixes[i]==null){//注意这个条件
                prefixes[i]=stringArray[i];
            }
        }
        return prefixes;
    }
}
