package tier.leetc211_m;

import java.util.*;

public class WordDictionary {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isWord = false;
        }
    }

    /** Initialize your data structure here. */

    private TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.children.containsKey(word.charAt(i))) {
                node.children.put(word.charAt(i), new TrieNode());
            }
            node = node.children.get(word.charAt(i));
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        Queue<TrieNode> searchQ = new LinkedList<>();
        Queue<TrieNode> cacheQ = new LinkedList<>();

        if (word.charAt(0) == '.') {
            searchQ.addAll(root.children.values());
        } else if (root.children.containsKey(word.charAt(0))) {
            searchQ.add(root.children.get(word.charAt(0)));
        } else {
            return false;
        }

        for (int i = 1; i < word.length(); i++) {
            while (!searchQ.isEmpty()) {
                TrieNode node = searchQ.poll();
                char c = word.charAt(i);

                if (c == '.') {
                    cacheQ.addAll(node.children.values());
                } else if (node.children.containsKey(c)) {
                    cacheQ.add(node.children.get(c));
                }

            }
            //注意这个条件是判断 cacheQ.size()
            if (cacheQ.size() == 0)
                return false;
            searchQ.addAll(cacheQ);
            cacheQ.clear();
        }

        for (TrieNode node : searchQ) {
            if (node.isWord) {
                return true;
            }
        }

        return false;
    }
}
