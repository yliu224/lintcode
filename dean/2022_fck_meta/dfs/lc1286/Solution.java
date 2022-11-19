package dfs.lc1286;

import java.util.*;

public class Solution {
    private List<String> list;
    private int index;
    public Solution(String characters, int combinationLength) {
        this.list = new ArrayList<>();
        DFS(characters,new StringBuilder(),combinationLength);
        this.index = 0;
    }

    private void DFS(String s, StringBuilder path, int left) {
        if (left == 0) {
            list.add(path.toString());
            return;
        }
        for (int i = 0; i < s.toCharArray().length; i++) {
            path.append(s.charAt(i));
            DFS(s.substring(i + 1), path, left - 1);
            path.deleteCharAt(path.length()-1);
        }
    }

    public String next() {
        return index<=list.size()-1?list.get(index++):null;
    }

    public boolean hasNext() {
        return index<=list.size()-1;
    }
}
