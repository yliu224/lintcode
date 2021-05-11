package monotone_stack.lc126_e;

import java.util.Deque;
import java.util.LinkedList;

//TLE
//https://share.jiuzhang.com/solution?id=142551
public class Solution {
    /**
     * @param nums: a list of integers
     * @return: The xorsum of maximun value xor minimun value of all subintervals
     */
    public int XorSum(int[] nums) {
        if(nums.length==0) return 0;
        //  write your code here
        int xor = getXOR(nums, 1);
        for(int i=2;i<=nums.length;i++){
            xor = xor ^ getXOR(nums,i);
        }
        return xor;
    }
    
    private int getXOR(int[] nums,int size){
        Deque<Integer> aseQ = new LinkedList<>();
        Deque<Integer> decQ = new LinkedList<>();

        for(int i=0;i<size;i++){
            while(!aseQ.isEmpty() && nums[aseQ.getLast()]>nums[i]){
                aseQ.removeLast();
            }
            aseQ.addLast(i);

            while(!decQ.isEmpty() && nums[decQ.getLast()]<nums[i]){
                decQ.removeLast();
            }
            decQ.addLast(i);
        }

        int xor = nums[aseQ.getFirst()] ^ nums[decQ.getFirst()];
        for(int i=size;i<nums.length;i++){
            while(!aseQ.isEmpty() && nums[aseQ.getLast()]>nums[i]){
                aseQ.removeLast();
            }
            while(!aseQ.isEmpty() && Math.abs(aseQ.getFirst()-i)+1>size){
                aseQ.removeFirst();
            }
            aseQ.addLast(i);

            while(!decQ.isEmpty() && nums[decQ.getLast()]<nums[i]){
                decQ.removeLast();
            }
            while(!decQ.isEmpty() && Math.abs(decQ.getFirst()-i)+1>size){
                decQ.removeFirst();
            }
            decQ.addLast(i);
            xor = xor ^ (nums[aseQ.getFirst()] ^ nums[decQ.getFirst()]);
        }
        return xor;
    }
}
