package two_pointer.lc80;

public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int curValue = nums[0];
        int count = 1;

        int writeIndex = 1;
        for (int readIndex = 1; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] == curValue) {
                count++;
                if (count <= 2) {
                    nums[writeIndex++] = nums[readIndex];
                }
            } else {
                curValue = nums[readIndex];
                count = 1;
                nums[writeIndex++] = nums[readIndex];
            }
        }

        return writeIndex;
    }
}
