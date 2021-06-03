package string.leetc557_e;

public class Solution {
    public String reverseWords(String s) {
        String[] splitedString = s.split(" ");
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for(int i=0;i<splitedString.length;i++){
            sb.replace(0, sb.length(), splitedString[i]);
            result.append(sb.reverse().toString()+" ");
        }

        result.deleteCharAt(result.length()-1);
        return result.toString();
    }
}
