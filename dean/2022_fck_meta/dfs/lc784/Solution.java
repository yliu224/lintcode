package dfs.lc784;

import java.util.*;

public class Solution {
    private List<String> result = new ArrayList<>();
    private int len;

    public List<String> letterCasePermutation(String s) {
        len = s.length();
        DFS(s, new StringBuilder());
        return result;
    }

    private void DFS(String s, StringBuilder path) {
        if (path.length() == len) {
            result.add(path.toString());
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                path.append(Character.toUpperCase(c));
                DFS(s.substring(i + 1), path);
                path.deleteCharAt(path.length() - 1);
            } else if (c >= 'A' && c <= 'Z') {
                path.append(Character.toLowerCase(c));
                DFS(s.substring(i + 1), path);
                path.deleteCharAt(path.length() - 1);
            }
            path.append(c);
            DFS(s.substring(i + 1), path);
            path.deleteCharAt(path.length() - 1);
        }

    }
}