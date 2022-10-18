package trie.lc336;

import java.util.*;

public class Solution {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
        String word = "";
        int index = 0;
    }

    private TrieNode root = new TrieNode();

    public List<List<Integer>> palindromePairs(String[] words) {
        //buildTree
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                node = node.children.computeIfAbsent(c, x -> new TrieNode());
            }
            node.isWord = true;
            node.word = s;
            node.index = i;
        }
        List<List<Integer>> result = new ArrayList<>();
        //find result
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            TrieNode node = root;
            int j = s.toCharArray().length - 1;
            for (; j >= 0; j--) {
                char c = s.charAt(j);
                if (node.children.containsKey(c)) {
                    node = node.children.get(c);
                    if (node.isWord && i != node.index && isPali(node.word, s)) {
                        result.add(new ArrayList<>(Arrays.asList(node.index, i)));
                    }
                } else {
                    break;
                }
            }
            //注意多思考一些特殊条件
            if (j == -1) {
                BSFFindPali(s, node, result, i);
            }
        }

        return result;
    }

    private void BSFFindPali(String s, TrieNode node, List<List<Integer>> result, int j) {
        Queue<TrieNode> q = new ArrayDeque<>();
        node.children.values().stream().forEach(q::offer);
        while (!q.isEmpty()) {
            TrieNode cur = q.poll();
            q.addAll(cur.children.values());
            if (cur.isWord && j != cur.index && isPali(cur.word, s)) {
                result.add(new ArrayList<>(Arrays.asList(cur.index, j)));
                if (s.equals("")) {
                    result.add(new ArrayList<>(Arrays.asList(j, cur.index)));
                }
            }
        }
    }

    private boolean isPali(String s1, String s2) {
        String s = s1 + s2;
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
