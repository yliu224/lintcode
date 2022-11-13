package dfs.lc93;

import java.util.*;

class Solution {
    private List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        DFS(s, new LinkedList<>(), 0);
        return result;
    }

    private void DFS(String s, LinkedList<String> path, int depth) {
        if (depth == 3) {
            int val = getInt(s);
            if (val >= 0 && val <= 255) {
                path.addLast(s);
                result.add(path.stream().reduce((a, b) -> a + "." + b).get().toString());
                path.removeLast();
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            String valStr = s.substring(0, Math.min(i + 1, s.length()));
            int val = getInt(valStr);
            if (val >= 0 && val <= 255) {
                path.addLast(valStr);
                DFS(s.substring(Math.min(i + 1, s.length())), path, depth + 1);
                path.removeLast();
            }
        }
    }

    private int getInt(String s) {
        if (s.isEmpty() || (s.length() > 1 && s.startsWith("0"))) {
            return -1;
        }
        try {
            //注意这儿可能超出范围
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }
}
