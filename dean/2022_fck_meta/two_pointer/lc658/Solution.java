package two_pointer.lc658;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int position = binarySearch(arr, x);
        System.out.println(position);
        int l = position - 1, r = position + 1;
        int[] result = new int[k];
        result[0] = arr[position];
        int i = 1;
        while (l >= 0 && r <= arr.length - 1 && i < k) {
            int ld = Math.abs(arr[l] - x);
            int rd = Math.abs(arr[r] - x);
            if (ld > rd) {
                result[i++] = arr[r++];
            } else {
                result[i++] = arr[l--];
            }
        }
        while (i < k && l >= 0) {
            result[i++] = arr[l--];
        }
        while (i < k && r <= arr.length - 1) {
            result[i++] = arr[r++];
        }
        return Arrays.stream(result).boxed().sorted().collect(Collectors.toList());
    }

    int binarySearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (arr[mid] > x) {
                r = mid;
            } else {
                l = mid;
            }
        }
        if (arr[r] == x) {
            return r;
        }
        if (arr[l] == l) {
            return l;
        }
        return Math.abs(arr[l] - x) > Math.abs(arr[r] - x) ? r : l;
    }
}
