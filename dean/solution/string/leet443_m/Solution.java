package string.leet443_m;

class Solution {
    public int compress(char[] chars) {
        if (chars.length < 2) {
            return 1;
        }

        int count = 1;
        int position = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                chars[position++] = chars[i - 1];
                if (count == 1) {
                    continue;
                }
                String strCount = String.valueOf(count);
                for (int j = 0; j < strCount.length(); j++) {
                    chars[position++] = strCount.charAt(j);
                }
                count = 1;
            } else {
                count++;
            }
        }

        // 注意这个后面一定要判断最后一个
        chars[position++] = chars[chars.length - 1];
        if (count != 1) {
            String strCount = String.valueOf(count);
            for (int j = 0; j < strCount.length(); j++) {
                chars[position++] = strCount.charAt(j);
            }
        }

        return position;
    }
}
