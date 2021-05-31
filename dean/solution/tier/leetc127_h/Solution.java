package tier.leetc127_h;

import java.util.*;

//BFS+Trie
public class Solution {
    class TrieNode {
        public Map<Character, TrieNode> children;
        public boolean isWord;
        public String word;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isWord = false;
            this.word = "";
        }
    }

    private TrieNode root;
    private int minStep;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        root = new TrieNode();
        buildTree(wordList);
        minStep = Integer.MAX_VALUE;
        if (searchWord(endWord).size() == 0) {
            return 0;
        }

        int step = 0;
        Queue<String> q = new LinkedList<>();
        Queue<String> cacheQ = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(beginWord);
        visited.add(beginWord);
        while (!q.isEmpty()) {
            step++;
            while (!q.isEmpty()) {
                StringBuilder begin = new StringBuilder(q.poll());
                if (begin.toString().equals(endWord)) {
                    minStep = Math.min(minStep, step);
                    break;
                }
                for (int i = 0; i < begin.toString().length(); i++) {
                    char c = begin.charAt(i);
                    begin.replace(i, i + 1, ".");
                    List<String> candidates = searchWord(begin.toString());
                    for (String s : candidates) {
                        if (!visited.contains(s)) {
                            visited.add(s);
                            cacheQ.add(s);
                        }
                    }
                    begin.replace(i, i + 1, String.valueOf(c));
                }
            }
            q.addAll(cacheQ);
            cacheQ.clear();
        }
        return minStep == Integer.MAX_VALUE ? 0 : minStep;
    }

    private void buildTree(List<String> wordList) {
        for (String s : wordList) {
            TrieNode node = root;
            for (int i = 0; i < s.length(); i++) {
                if (!node.children.containsKey(s.charAt(i))) {
                    node.children.put(s.charAt(i), new TrieNode());
                }
                node = node.children.get(s.charAt(i));
            }
            node.isWord = true;
            node.word = s;
        }
    }

    private List<String> searchWord(String s) {
        List<String> matched = new ArrayList<>();
        int i = 0;
        Queue<TrieNode> q = new LinkedList<>();
        Queue<TrieNode> cacheQ = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty() && i < s.length()) {
            while (!q.isEmpty() && i < s.length()) {
                TrieNode node = q.poll();
                if (s.charAt(i) == '.') {
                    cacheQ.addAll(node.children.values());
                } else if (node.children.containsKey(s.charAt(i))) {
                    cacheQ.add(node.children.get(s.charAt(i)));
                }
            }
            q.addAll(cacheQ);
            cacheQ.clear();
            i++;
        }

        for (TrieNode node : q) {
            if (node.isWord) {
                matched.add(node.word);
            }
        }
        return matched;
    }
}
