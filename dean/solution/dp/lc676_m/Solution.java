package dp.lc676_m;

public class Solution {
    private int MOD = 1000000007;
    /**
     * @param s: a message being encoded
     * @return: an integer
     */
    //dp[i]=dp[i-1]+dp[i-2]
    //dp[i]+=(dp[i-1]*getCombinationNumber(s.substring(i-1,i)))%MOD;
    //dp[i]+=(dp[i-2]*getCombinationNumber(s.substring(i-2,i)))%MOD;
    //当dp[i]为0，或者*的时候要特殊处理
    public int numDecodings(String s) {
        if(s.length()==0 || s.equals("")) return 0;
        if(s.length()==1) return s.charAt(0) == '*' ? 9 : 1;
        long[] dp = new long[s.length()+1];

        dp[0]=1;
        dp[1]= s.charAt(0) == '*' ? 9 : 1;

        for(int i=2;i<s.length()+1;i++){
            dp[i]+=(dp[i-1]*getCombinationNumber(s.substring(i-1,i)))%MOD;
            dp[i]+=(dp[i-2]*getCombinationNumber(s.substring(i-2,i)))%MOD;
        }

        return (int)dp[s.length()]%MOD;
    }

    private int getCombinationNumber(String s){
        if(s.length()==1){
            if(s.equals("*")){
                return 9;
            }
            if(s.equals("0")){
                return 0;
            }
            return 1;
        } else{
            if(s.equals("**")){
                //11->19, 21->26
                return 15;
            }
            if(s.charAt(0)=='0'){
                return 0;
            }
            if(s.charAt(0)!='*' && s.charAt(1)=='*'){
                //1*,2*,3*,0*
                if(s.charAt(0)=='1'){
                    return 9;
                }
                if(s.charAt(0)=='2'){
                    return 6;
                }
                return 0;
            }
            if(s.charAt(0)=='*' && s.charAt(1)!='*'){
                //*1,*2,*3...
                if(s.charAt(1)>='0'&& s.charAt(1)<='6'){
                    return 2;
                }
                return 1;
            }

            int number = Integer.parseInt(s);
            if(number>=10 && number<=26){
                return 1;
            }
            return 0;
        }
    }
}
