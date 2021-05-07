package tier.lc473_m;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {
    class TrieNode {
    public Map<Character,TrieNode> children;
    public boolean isWord;
    public String word;

    public TrieNode(){
        children = new HashMap<>();
        isWord = false;
        word = "";
        }
    }
    private TrieNode root = new TrieNode();
    /*
     * @param word: Adds a word into the data structure.
     * @return: nothing
     */
    public void addWord(String word) {
        TrieNode tree = root;
        for(char c:word.toCharArray()){
            if(!tree.children.containsKey(c)){
                tree.children.put(c,new TrieNode());
            }
            tree = tree.children.get(c);
        }
        tree.isWord = true;
        tree.word = word;
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        return search(word,root);
    }

    private boolean search(String word,TrieNode rootNode){
        TrieNode node = rootNode;
        if(word.length()==0 || node.children.size()==0){
            if(word.length()!=0){//注意这儿的判断条件
                return false;
            }
            return node.isWord;
        }

        char c = word.charAt(0);
        String subStr = word.substring(1);

        if(c=='.'){
            boolean result=false;
            for(TrieNode n:node.children.values()){
                result = result | search(subStr,n);
            }
            return result;
        } else{
            if(node.children.containsKey(c)){
                return search(subStr,node.children.get(c));
            }
            return false;
        }
    }
}
