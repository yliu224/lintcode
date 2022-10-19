package union_find.lc990;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private Map<Character, Character> set = new HashMap<>();

    public boolean equationsPossible(String[] equations) {
        for (String s : equations) {
            char a = s.charAt(0);
            char b = s.charAt(3);

            String e = s.substring(1, 3);
            if (e.equals("==")) {
                merge(a, b);
            }
        }

        for (String s : equations) {
            char a = s.charAt(0);
            char b = s.charAt(3);

            String e = s.substring(1, 3);
            if (e.equals("!=")) {
                if (compressFind(a) == compressFind(b)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void merge(char a, char b) {
        char roota = compressFind(a);
        char rootb = compressFind(b);

        if (roota != rootb) {
            set.put(roota, rootb);
        }
    }

    private char compressFind(char c) {
        char root = c;
        while (set.get(root) != null) {
            root = set.get(root);
        }

        while (set.get(c) != null) {
            char tmp = set.get(c);
            set.put(c, root);
            c = tmp;
        }
        return root;
    }
}
