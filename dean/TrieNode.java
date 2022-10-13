

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public Map<Character,TrieNode> children;
    public boolean isWord;
    public String word;

    public TrieNode(){
        children = new HashMap<>();
        isWord = false;
        word = "";
    }
}