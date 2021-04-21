package lc1310_e;

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the product of all the elements of nums except nums[i].
     */
    public int[] productExceptSelf(int[] nums) {
        // write your code here
        //Is the product of all numbers in the Integer.MAX_VALUE? N
        //Is there a 0? Y
        //Is there any negtive numbers Y
        
        int[] prefixProduct = new int[nums.length+1];
        int[] sufixProduct = new int[nums.length+1];
        int[] result = new int[nums.length];
        
        prefixProduct[0]=1;
        sufixProduct[nums.length]=1;
        for(int i=0;i<nums.length;i++){
            prefixProduct[i+1]=prefixProduct[i]*nums[i];
        }
        
        for(int i=nums.length-1;i>=0;i--){
            sufixProduct[i]=sufixProduct[i+1]*nums[i];
        }
        
        for(int i=0;i<nums.length;i++){
            result[i]=prefixProduct[i]*sufixProduct[i+1];
        }
        
        
        return result;
    }
}
