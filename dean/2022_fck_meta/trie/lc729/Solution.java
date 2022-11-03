package trie.lc729;

import java.util.*;

public class Solution {
    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;
        int count;
        Set<Character> visited;
        boolean hasCounted;

        TrieNode() {
            children = new HashMap<>();
            visited = new HashSet<>();
            isWord = false;
            count = 0;
            hasCounted = false;
        }
    }

    private TrieNode root = new TrieNode();

    public int numMatchingSubseq(String s, String[] words) {
        for (String ss : words) {
            TrieNode node = root;
            for (char c : ss.toCharArray()) {
                node = node.children.computeIfAbsent(c, x -> new TrieNode());
            }
            node.isWord = true;
            node.count++;
        }

        int count = 0;
        Queue<TrieNode> q = new ArrayDeque<>();
        Queue<TrieNode> swap = new ArrayDeque<>();
        q.add(root);
        for (char c : s.toCharArray()) {
            while (!q.isEmpty()) {
                TrieNode cur = q.poll();
                //注意这儿，count过的不要再count
                if (cur.isWord && !cur.hasCounted) {
                    count += cur.count;
                    cur.hasCounted = true;
                }
                if (cur.children.containsKey(c) && !cur.visited.contains(c)) {
                    cur.visited.add(c);
                    swap.add(cur.children.get(c));
                }
                if (cur.visited.size() != cur.children.size()) {
                    swap.add(cur);
                }
            }
            q.addAll(swap);
            swap.clear();
        }
        //注意这儿完了以后，还要再 for 一次
        while (!q.isEmpty()) {
            TrieNode cur = q.poll();
            if (cur.isWord && !cur.hasCounted) {
                count += cur.count;
                cur.hasCounted = true;
            }
        }
        return count;
    }
}
