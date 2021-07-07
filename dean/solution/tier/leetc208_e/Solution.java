package tier.leetc208_e;

import java.util.*;

public class Solution {

    class TrieNode{
        Map<Character,TrieNode> children;
        String word;
        boolean isWord;
        
        public TrieNode(){
            this.children = new HashMap<>();
            this.word = "";
            this.isWord = false;
        }
    }
    /** Initialize your data structure here. */
    private TrieNode root;
    public Solution() {
        this.root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for(int i=0;i<word.length();i++){
            TrieNode child = node.children.getOrDefault(word.charAt(i),new TrieNode());
            node.children.put(word.charAt(i),child);
            node = child;
        }
        node.word = word;
        node.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for(int i=0;i<word.length();i++){
            if(node.children.containsKey(word.charAt(i))){
                node = node.children.get(word.charAt(i));
            } else{
                return false;
            }
        }
        return node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(int i=0;i<prefix.length();i++){
            if(node.children.containsKey(prefix.charAt(i))){
                node = node.children.get(prefix.charAt(i));
            } else{
                return false;
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
