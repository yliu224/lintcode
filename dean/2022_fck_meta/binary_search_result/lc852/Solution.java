package binary_search_result.lc852;

public class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (asc(arr, mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return isPeek(arr, l) ? l : r;
    }

    private boolean isPeek(int[] arr, int index) {
        if (index == 0 || index == arr.length - 1) {
            return false;
        }
        return arr[index - 1] < arr[index] && arr[index] > arr[index + 1];
    }

    private boolean asc(int[] arr, int index) {
        if (index != arr.length - 1) {
            return arr[index] < arr[index + 1];
        }
        return arr[index - 1] < arr[index];
    }
}
