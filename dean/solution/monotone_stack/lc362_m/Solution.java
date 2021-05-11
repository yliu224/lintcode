package monotone_stack.lc362_m;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//标准的Deque使用范例 
public class Solution {
    /**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0 || k==0) return new ArrayList<>();
        Deque<Integer> dq = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<k;i++){
            while(!dq.isEmpty() && !isValid(nums[dq.getLast()],nums[i])){
                dq.removeLast();
            }
            dq.addLast(i);
        }
        result.add(nums[dq.getFirst()]);

        for(int i=k;i<nums.length;i++){
            while(!dq.isEmpty() && !isValid(nums[dq.getLast()],nums[i])){
                dq.removeLast();
            }
            while(!dq.isEmpty() && Math.abs(i-dq.getFirst())+1>k){
                dq.removeFirst();
            }
            dq.addLast(i);
            result.add(nums[dq.getFirst()]);
        }

        return result;
    }    

    private boolean isValid(int stackValue,int inComingValue){
        return stackValue>=inComingValue;
    }
}
