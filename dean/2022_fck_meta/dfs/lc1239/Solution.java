package dfs.lc1239;

import java.util.*;

public class Solution {
    public int maxLength(List<String> arr) {
        int[] bitArray = getBitArr(arr);
        return DFS(bitArray, 0, 0, arr, 0);
    }

    private int DFS(int[] array, int index, int path, List<String> arr, int pathLen) {
        int max = pathLen;
        for (int i = index; i < array.length; i++) {
            if (array[i] == -1) {
                continue;
            }
            if ((path & array[i]) == 0) {
                path = path | array[i];
                pathLen += arr.get(i).length();
                max = Math.max(max, DFS(array, i, path, arr, pathLen));
                pathLen -= arr.get(i).length();
                path = path ^ array[i];
            }
        }
        return max;
    }

    private int[] getBitArr(List<String> arr) {
        int[] result = new int[arr.size()];
        boolean canSkip = false;
        for (int j = 0; j < arr.size(); j++) {
            String s = arr.get(j);
            int n = 0;
            canSkip = false;
            for (int i = 0; i < s.toCharArray().length; i++) {
                int next = 1 << (s.charAt(i) - 'a');
                if ((next & n) != 0) {
                    canSkip = true;
                    break;
                } else {
                    n |= next;
                }
            }
            result[j] = canSkip ? -1 : n;
        }
        //System.out.println(Arrays.toString(result));
        return result;
    }
}
