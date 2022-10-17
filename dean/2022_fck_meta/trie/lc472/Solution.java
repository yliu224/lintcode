package trie.lc472;

import java.util.*;

public class Solution {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
        String word = "";
    }

    Set<String> target = new HashSet<>();
    TrieNode root = new TrieNode();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        //build tree
        for (String s : words) {
            TrieNode cur = root;
            for (char c : s.toCharArray()) {
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TrieNode());
                }
                cur = cur.children.get(c);
            }
            cur.isWord = true;
            cur.word = s;
        }

        //DFS
        for (String s : words) {
            DFS(s, s, 0);
        }
        return new ArrayList<>(target);
    }

    private void DFS(String s, String originalWord, int depth) {
        if (s.length() == 0) {
            if (depth >= 2) {
                target.add(originalWord);
            }
            return;
        }
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            if (node.children.containsKey(s.charAt(i))) {
                node = node.children.get(s.charAt(i));
                if (node.isWord) {
                    DFS(s.substring(i + 1), originalWord, depth + 1);
                }
            } else {
                return;
            }
        }
    }
}
