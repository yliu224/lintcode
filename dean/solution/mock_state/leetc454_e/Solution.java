package mock_state.leetc454_e;

import java.util.*;

public class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> oneTwo = new HashMap<>();
        Map<Integer, Integer> threeFour = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                oneTwo.put(nums1[i] + nums2[j], oneTwo.getOrDefault(nums1[i] + nums2[j], 0) + 1);
            }
        }

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                threeFour.put(nums3[i] + nums4[j], threeFour.getOrDefault(nums3[i] + nums4[j], 0) + 1);
            }
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> e : oneTwo.entrySet()) {
            if (threeFour.containsKey(0 - e.getKey())) {
                count += e.getValue() * threeFour.get(0 - e.getKey());
            }
        }
        return count;
    }
}
