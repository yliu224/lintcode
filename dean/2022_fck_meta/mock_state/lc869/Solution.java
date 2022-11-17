package mock_state.lc869;

import java.util.Arrays;

public class Solution {
    public boolean reorderedPowerOf2(int n) {
        int[] p2 = new int[30];
        p2[0] = 1;
        for (int i = 1; i < 30; i++) {
            p2[i] = p2[i - 1] * 2;
        }

        int[] digits = new int[10];
        int len = 0;
        while (n != 0) {
            digits[n % 10]++;
            n = n / 10;
            len++;
        }

        for (int i = 0; i < 30; i++) {
            int[] a = Arrays.copyOf(digits,digits.length);
            if(isMatch(a,len,p2[i])){
                return true;
            }
        }
        return false;
    }

    private boolean isMatch(int[] d,int len,int target){
        while(len>0 && target!=0){
            if(d[target%10]!=0){
                d[target%10]--;
                len--;
                target = target/10;
            } else {
                break;
            }
        }
        //注意这个return 条件
        return len==0 && target==0;
    }
}
