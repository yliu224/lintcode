package mock_state.lc1332_e;

public class Solution {
    
    public int hammingWeight(int n) {
        int count =0;
        while(n!=0){
            if(n%2==1) count++;
            n=n/2;
        }
        return count;
    }
}
