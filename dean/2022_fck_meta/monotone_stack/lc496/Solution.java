package monotone_stack.lc496;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[nums2.length];
        Arrays.fill(ans, -1);
        Map<Integer, Integer> revertIndex = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            revertIndex.put(nums2[i], i);
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                int index = stack.pop();
                ans[index] = nums2[i];
            }
            stack.push(i);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = ans[revertIndex.get(nums1[i])];
        }
        return result;
    }
}
