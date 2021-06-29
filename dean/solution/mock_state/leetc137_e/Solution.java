package mock_state.leetc137_e;

public class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            counts.put(nums[i], counts.getOrDefault(nums[i], 0) + 1);
            int count = counts.get(nums[i]);
            if (count == 3) {
                counts.remove(nums[i]);
            }
        }

        return (int) counts.keySet().toArray()[0];
    }
}
