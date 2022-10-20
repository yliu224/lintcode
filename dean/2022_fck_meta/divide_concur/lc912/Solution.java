package divide_concur.lc912;

public class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int l, int r) {
        if (r - l <= 1) {
            if (nums[l] > nums[r]) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            }
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);

        int[] merged = new int[r - l + 1];
        int m1 = l, m2 = mid + 1;
        int index = 0;
        while (m1 <= mid && m2 <= r) {
            if (nums[m1] < nums[m2]) {
                merged[index++] = nums[m1++];
            } else {
                merged[index++] = nums[m2++];
            }
        }
        while (m1 <= mid) {
            merged[index++] = nums[m1++];
        }
        while (m2 <= r) {
            merged[index++] = nums[m2++];
        }
        m1 = l;
        for (int i = 0; i < merged.length; i++) {
            nums[m1++] = merged[i];
        }
    }
}
