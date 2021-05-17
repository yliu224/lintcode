package string.lc1887_m;

public class Solution {
    //注意理解题目意思
    /**
     * @param S: the string
     * @return: The numbers of strings
     */
    public long stretchWord(String S) {
        long sum=1;
        int count = 1;
        char c='0';
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)==c){
                count++;
            } else{
                count =1;
                c=S.charAt(i);
            }

            if(count==2){
                sum*=2;
            }
        }

        return sum;
    }
}