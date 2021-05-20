package binary_search.lc1507_h;

import java.util.TreeMap;

public class Solution {
    // [2,-1,2],3
    // [F,F,T]

    // [1,2,4,-3,5,-1], 6
    // [F,T,T,T,T,T]

    // [1,2,4,-3,5,-1], 7
    // [F,F,T,T,T,T]

    // [1,2,4,-3,5,-1], 8
    // [F,F,F,T,T,T]

    // [84,-37,32,40,95], 167
    // [F, F, T, T, T]

    // [2, -1, 2], 3
    // [F, F, T]

    //关键点
    //1. left从1 开始
    //2. 使用TreeMap
    //3. left==right
    //4. 反复研究 isValid 函数！！
    public int shortestSubarray(int[] A, int K) {
        int[] prefixSum = getPrefixSum(A);
        if (A.length == 0) {
            return -1;
        }
        int left = 1;// min length is 1
        int right = A.length;// max length is A.length
        // for (int i = 0; i < A.length; i++) {
        //     System.out.println(String.valueOf(i + 1) + ":" + isValid(i + 1, prefixSum, K));
        // }
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (isValid(mid, prefixSum, K)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (left == right) {
            if (A[0] >= K) {
                return 1;
            }
            return -1;
        }

        boolean isLeftValid = isValid(left, prefixSum, K);
        boolean isRightValid = isValid(right, prefixSum, K);

        if(!isLeftValid){
            return left;
        }

        if(!isRightValid){
            return right;
        }

        return -1;
    }

    private int[] getPrefixSum(int[] A) {
        int[] prefixSum = new int[A.length + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < A.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + A[i];
        }
        return prefixSum;
    }

    // 定义：在A里面，是否存在长度小于len的子串的sum大于target
    // 就是说，如果在prefixSum[i]-prefixSum[i-1,i-2,i-3...i-len+1]有任意一个值大于等于target，return
    // false
    // 也就是说，如果prefixSum[i]-min(prefixSum[i-1,i-2,i-3...i-len+1])>=target, return
    // false
    private boolean isValid(int len, int[] prefixSum, int target) {
        TreeMap<Integer,Integer> tm = new TreeMap<>((a,b)->a-b);//Sort by key
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < prefixSum.length; i++) {
            if (tm.size() > len-1) {
                tm.remove(prefixSum[i-len-1],i-len-1);
            }
            tm.put(prefixSum[i - 1],i-1);
            max = Math.max(max, prefixSum[i] - tm.firstKey());
            if (max >= target) {
                return false;
            }
        }
        return true;
    }
}
