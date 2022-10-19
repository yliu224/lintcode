package union_find.lc128;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private Map<Integer, Integer> set = new HashMap<>();
    private Map<Integer, Integer> setSize = new HashMap<>();

    public int longestConsecutive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (set.containsKey(nums[i])) {
                continue;
            }
            set.put(nums[i], null);
            setSize.put(nums[i], 1);
            if (set.containsKey(nums[i] - 1)) {
                merge(nums[i] - 1, nums[i]);
            }
            if (set.containsKey(nums[i] + 1)) {
                merge(nums[i] + 1, nums[i]);
            }
        }
        int max = 0;
        for (int m : setSize.values()) {
            max = Math.max(m, max);
        }

        return max;
    }

    private void merge(int x, int y) {
        int rootx = compressFind(x);
        int rooty = compressFind(y);

        if (rootx != rooty) {
            set.put(rootx, rooty);
            setSize.put(rooty, setSize.get(rootx) + setSize.get(rooty));
            setSize.remove(rootx);
        }
    }

    private int compressFind(int x) {
        int root = x;
        while (set.get(root) != null) {
            root = set.get(root);
        }

        while (set.get(x) != null) {
            int tmp = set.get(x);
            set.put(x, root);
            x = tmp;
        }
        return root;
    }
}

