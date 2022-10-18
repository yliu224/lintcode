package trie.lc745;

import java.util.*;

public class Solution {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
        String word = "";
        int index;
    }

    private TrieNode prefRoot = new TrieNode();
    private TrieNode sufRoot = new TrieNode();

    public Solution(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            TrieNode node = prefRoot;
            for (char c : s.toCharArray()) {
                node = node.children.computeIfAbsent(c, x -> new TrieNode());
            }
            node.isWord = true;
            node.word = s;
            node.index = i;
        }
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            TrieNode node = sufRoot;
            for (int j = s.toCharArray().length - 1; j >= 0; j--) {
                node = node.children.computeIfAbsent(s.charAt(j), x -> new TrieNode());
            }
            node.isWord = true;
            node.word = s;
            node.index = i;
        }
    }

    public int f(String pref, String suff) {
        int i = 0;
        TrieNode node = prefRoot;
        for (; i < pref.length(); i++) {
            if (node.children.containsKey(pref.charAt(i))) {
                node = node.children.get(pref.charAt(i));
            } else {
                break;
            }
        }
        if (i != pref.length()) {
            return -1;
        }

        Set<String> prefSet = BFS(node);

        node = sufRoot;
        i = suff.length() - 1;
        for (; i >= 0; i--) {
            if (node.children.containsKey(suff.charAt(i))) {
                node = node.children.get(suff.charAt(i));
            } else {
                break;
            }
        }
        if (i != -1) {
            return -1;
        }

        return findLargest(node, prefSet);
    }

    private int findLargest(TrieNode cnode, Set<String> prefSet) {
        Queue<TrieNode> q = new ArrayDeque<>();
        int maxI = -1;
        q.offer(cnode);

        while (!q.isEmpty()) {
            TrieNode node = q.poll();
            if (node.isWord && prefSet.contains(node.word)) {
                if (maxI < node.index) {
                    maxI = node.index;
                }
            }
            for (TrieNode n : node.children.values()) {
                q.offer(n);
            }
        }
        return maxI;
    }

    private Set<String> BFS(TrieNode cnode) {
        Queue<TrieNode> q = new ArrayDeque<>();
        Set<String> prefSet = new HashSet<>();
        q.offer(cnode);

        while (!q.isEmpty()) {
            TrieNode node = q.poll();
            if (node.isWord) {
                prefSet.add(node.word);
            }
            for (TrieNode n : node.children.values()) {
                q.offer(n);
            }
        }
        return prefSet;
    }
}
