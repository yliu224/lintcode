package two_pointer.lc75;

public class Solution {
    public void sortColors(int[] nums) {
        int tmp;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                tmp = nums[index];
                nums[index] = 0;
                nums[i] = tmp;
                index++;
            }
        }
        for (int i = index; i < nums.length; i++) {
            if (nums[i] == 1) {
                tmp = nums[index];
                nums[index] = 1;
                nums[i] = tmp;
                index++;
            }
        }
        //System.out.println(Arrays.toString(nums));
    }
}
