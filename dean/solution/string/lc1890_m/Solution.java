package string.lc1890_m;


public class Solution {
    public String formMinimumNumber(String str) {
        int[] result = new int[str.length()+1];
        result[0]=1;
        int digit = 2;

        for(int i=0;i<str.length();i++){
            result[i+1]=digit;
            digit++;
            int j=i;
            while(j>=0 && !isValid(result[j],result[j+1],str.charAt(j))){//注意这个判断条件
                int tmp = result[j];
                result[j]=result[j+1];
                result[j+1]=tmp;
                j--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<result.length;i++){
            sb.append(result[i]);
        }
        return sb.toString();
    }

    private boolean isValid(int a,int b,char c){
        if(c=='D'){
            return a>b;
        } else{
            return b>a;
        }
    }
}
