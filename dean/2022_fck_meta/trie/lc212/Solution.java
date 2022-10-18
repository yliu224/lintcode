package trie.lc212;

import java.util.*;

public class Solution {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
        String word = "";
    }

    TrieNode root = new TrieNode();
    Set<String> result = new HashSet<>();
    int len, height;

    public List<String> findWords(char[][] board, String[] words) {
        len = board[0].length;
        height = board.length;
        for (String s : words) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                node = node.children.computeIfAbsent(c, x -> new TrieNode());
            }
            node.isWord = true;
            node.word = s;
        }
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (root.children.containsKey(board[i][j])) {
                    visited.add(getIndex(i, j));
                    DFS(getIndex(i, j), board, visited, root.children.get(board[i][j]));
                    visited.clear();
                }

            }
        }

        return new ArrayList<>(result);
    }

    private void DFS(int cur, char[][] board, Set<Integer> visited, TrieNode node) {
        if (node.isWord) {
            result.add(node.word);
        }
        for (int dir = 0; dir < 4; dir++) {
            int next = move(cur, dir);
            int ni = getI(next);
            int nj = getJ(next);
            if (next != cur && node.children.containsKey(board[ni][nj]) && !visited.contains(next)) {
                visited.add(next);
                DFS(next, board, visited, node.children.get(board[ni][nj]));
                visited.remove(next);
            }
        }
    }

    private int move(int cur, int dir) {
        int i = getI(cur);
        int j = getJ(cur);
        switch (dir) {
            case 0 -> i++;
            case 1 -> i--;
            case 2 -> j++;
            case 3 -> j--;
        }
        if (i < 0 || i >= height || j < 0 || j >= len) {
            return cur;
        }
        return getIndex(i, j);
    }

    private int getIndex(int i, int j) {
        return i * len + j;
    }

    private int getI(int index) {
        return index / len;
    }

    private int getJ(int index) {
        return index % len;
    }
}
