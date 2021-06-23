package two_pointer.leetc6_m;

//注意判断题的类型
public class Solution {
    public String convert(String s, int numRows) {
        int interval = numRows == 1 ? 1 : (numRows - 1) * 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int round = 1;
            for (int j = i; j < s.length();) {
                if (i == 0 || i == numRows - 1) {
                    sb.append(s.charAt(j));
                    j = j + interval;
                } else {
                    sb.append(s.charAt(j));
                    j = j + (interval - i * 2);
                    if (j >= s.length())
                        break;
                    sb.append(s.charAt(j));
                    j = i + interval * round;
                    round++;
                }
            }
        }

        return sb.toString();
    }
}
