package string.leetc680_m;

public class Solution {
    public boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {

            if (s.charAt(start) != s.charAt(end)) {

                if (s.charAt(start) == s.charAt(end - 1) && isPalindrome(start, end - 1, s)) {
                    return true;
                } else if (s.charAt(start + 1) == s.charAt(end) && isPalindrome(start + 1, end, s)) {
                    return true;
                } else {
                    return false;
                }

            }

            start++;
            end--;
        }

        return true;
    }

    private boolean isPalindrome(int start, int end, String s) {
        StringBuilder prefixStr = new StringBuilder();
        StringBuilder suffixStr = new StringBuilder();

        //注意这人长度的操作
        int len = (end - start) + 1;
        for (int i = start; i <= end; i++) {
            if (i < start+len / 2) {
                prefixStr.append(s.charAt(i));
            }

            if (((len % 2 == 0) ? start + len / 2 : start + len / 2 + 1) <= i) {
                suffixStr.append(s.charAt(i));
            }
        }
        
        return prefixStr.toString().equals(suffixStr.reverse().toString());
    }
}
