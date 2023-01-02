package two_pointer.lc977;

public class Solution {
    public int[] sortedSquares(int[] nums) {
        int l = 0, r = nums.length - 1;
        int[] result = new int[nums.length];
        int index = r;
        while (l <= r) {
            int ln = nums[l] * nums[l];
            int rn = nums[r] * nums[r];
            if (ln > rn) {
                result[index--] = ln;
                l++;
            } else {
                result[index--] = rn;
                r--;
            }
            //System.out.println("l:"+l+" r:"+r+" i:"+index);
        }
        return result;
    }
}
