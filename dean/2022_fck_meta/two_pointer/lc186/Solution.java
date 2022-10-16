package two_pointer.lc186;

public class Solution {
    public void reverseWords(char[] s) {
        revert(0, s.length - 1, s);
        //rever word
        int start = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                revert(start, i - 1, s);
                start = i + 1;
            }
        }
        revert(start, s.length - 1, s);
    }

    private void revert(int l, int r, char[] s) {
        char temp;
        while (l < r) {
            temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }
}
