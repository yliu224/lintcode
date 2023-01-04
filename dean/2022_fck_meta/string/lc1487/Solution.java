package string.lc1487;

import java.util.*;

public class Solution {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < names.length; i++) {
            if (map.containsKey(names[i])) {
                int index = map.get(names[i]);
                String newName = getName(names[i], index++);
                while (map.containsKey(newName)) {
                    newName = getName(names[i], index++);
                }
                map.put(newName, 1);
                map.put(names[i], index);
                names[i] = newName;
            } else {
                map.put(names[i], 1);
            }
        }
        return names;
    }

    private String getName(String name, int index) {
        return name + "(" + index + ")";
    }
}
