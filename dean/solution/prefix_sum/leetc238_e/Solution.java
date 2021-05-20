package prefix_sum.leetc238_e;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] prefixProduct = getPrefixProduct(nums);
        int[] suffixProduct = getSuffixProduct(nums);
        
        int[] answer = new int[nums.length];
        
        for(int i=0;i<nums.length;i++){
            answer[i]=prefixProduct[i]*suffixProduct[i+1];
        }
        return answer;
    }
    
    private int[] getPrefixProduct(int[] nums){
        int[] prefixProduct = new int[nums.length+1];
        prefixProduct[0]=1;
        for(int i=0;i<nums.length;i++){
            prefixProduct[i+1]=prefixProduct[i]*nums[i];
        }
        return prefixProduct;
    }
    
    private int[] getSuffixProduct(int[] nums){
        int[] suffixProduct = new int[nums.length+1];
        suffixProduct[nums.length]=1;
        for(int i=nums.length-1;i>=0;i--){
            suffixProduct[i]=suffixProduct[i+1]*nums[i];
        }
        return suffixProduct;
    }
}
