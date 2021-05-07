package tier.lc442_m;

import java.util.HashMap;
import java.util.Map;

public class Trie {
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

    private TrieNode root;
    public Trie() {
        root =new TrieNode();
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        TrieNode node = root;
        for(char c:word.toCharArray()){
            if(!node.children.containsKey(c)){
                node.children.put(c,new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isWord = true;
        node.word = word;
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = root;
        for(char c:word.toCharArray()){
            if(node.children.containsKey(c)){
                node = node.children.get(c);
            } else{
                return false;
            }
        }
        return node.isWord;
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c:prefix.toCharArray()){
            if(node.children.containsKey(c)){
                node = node.children.get(c);
            } else{
                return false;
            }
        }
        return true;
    }
}
