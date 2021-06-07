package union_find.leect721_m;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private Map<String, String> set = new HashMap<>();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<Integer, String> nameMapping = new HashMap<>();

        for (int i = 0; i < accounts.size(); i++) {
            nameMapping.put(i, accounts.get(i).get(0));
            for (int j = 1; j < accounts.get(i).size(); j++) {
                if (j == 1) {
                    merge(String.valueOf(i), accounts.get(i).get(j));
                } else {
                    merge(accounts.get(i).get(j - 1), accounts.get(i).get(j));
                }
            }
        }

        Map<Integer, Set<String>> newAccountMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                int index = Integer.parseInt(compressed_find(accounts.get(i).get(j)));
                Set<String> emails = newAccountMap.getOrDefault(index, new HashSet<>());
                emails.add(accounts.get(i).get(j));
                newAccountMap.put(index, emails);
            }
        }

        List<List<String>> result = new ArrayList<>();

        for (Map.Entry<Integer, Set<String>> account : newAccountMap.entrySet()) {
            List<String> emails = account.getValue().stream().collect(Collectors.toList());
            Collections.sort(emails);
            emails.add(0, nameMapping.get(account.getKey()));
            result.add(emails);
        }

        Collections.<List<String>>sort(result, (a, b) -> a.get(0).compareTo(b.get(0)));
        return result;
    }

    private void merge(String x, String y) {
        String rootX = compressed_find(x);
        String rootY = compressed_find(y);

        if (!rootX.equals(rootY)) {
            if (isInteger(rootX)) {
                set.put(rootY, rootX);
            } else {
                set.put(rootX, rootY);
            }

        }
    }

    // Union Find主要就是这个compressed_find函数！！
    private String compressed_find(String x) {
        String root = x;
        while (set.get(root) != null) {
            root = set.get(root);// 注意这儿不要写错了！！！
        }

        while (set.get(x) != null) {
            String tmp = set.get(x);
            set.put(x, root);
            x = tmp;
        }

        return root;
    }

    private boolean isInteger(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
