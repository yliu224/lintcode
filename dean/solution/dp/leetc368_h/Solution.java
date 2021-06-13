package dp.leetc368_h;

import java.util.*;

public class Solution {
    // 区间DP
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // dp[i] means the max combination in 0...i
        // dp[i]=max(nums[i]%nums[j]==0?dp[j]+1:1)
        //                       {j:0->i}
        Map<Integer, List<Integer>> subSet = new HashMap<>();
        int[] dp = new int[nums.length + 5];

        Arrays.sort(nums);// 要Sort，很重要
        subSet.put(nums[0], Arrays.asList(nums[0]));
        dp[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            int newNum = nums[i];
            List<Integer> sub = new ArrayList<>();
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (newNum % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    sub.clear();
                    sub.addAll(subSet.get(nums[j]));
                }
            }
            sub.add(newNum);
            subSet.put(newNum, sub);
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (subSet.get(nums[i]).size() > result.size()) {
                result = subSet.get(nums[i]);
            }
        }

        return result;
    }
}
