package string.lc151;

public class Solution {
    public String reverseWords(String s) {
        String[] splited = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = splited.length - 1; i >= 0; i--) {
            if (splited[i] != "") {
                sb.append(splited[i]);
                sb.append(" ");
            }
        }

        return sb.toString().trim();
    }
}
