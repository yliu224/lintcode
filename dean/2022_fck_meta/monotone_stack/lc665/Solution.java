package monotone_stack.lc665;

import java.util.Stack;

public class Solution {
    public boolean checkPossibility(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int removeTargetI = -1;
        boolean canSkip = false;
        for (int i = 0; i < nums.length; i++) {
            //mono stack不一定要有while loop，也可以是if！
            if (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                if (removeTargetI != -1) return false;
                removeTargetI = getRemoveTarget(stack.peek(), nums);
                if (removeTargetI == -2) {
                    return false;
                }
                if (removeTargetI == stack.peek()) {
                    stack.pop();
                    stack.push(i);
                }
            } else {
                stack.push(i);
            }
        }
        return true;
    }

    private int getRemoveTarget(int t1, int[] nums) {
        int t2 = t1 + 1;
        int l = t1 - 1;
        int r = t2 + 1;
        int nl = l == -1 ? Integer.MIN_VALUE : nums[l];
        int nr = r == nums.length ? Integer.MAX_VALUE : nums[r];
        int nt1 = nums[t1];
        int nt2 = nums[t2];

        if (nt1 >= nl && nt1 <= nr) {
            return t2;
        }
        if (nt2 >= nl && nt2 <= nr) {
            return t1;
        }
        return -2;
    }
}
