package mock_state.leetc158_e;

import java.util.*;

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    private Deque<Character> cache = new LinkedList<>();

    public int read(char[] buf, int n) {
        char[] read4Data = new char[4];
        while (cache.size() < n) {
            int len = read4(read4Data);
            if (len == 0)
                break;
            for (int i = 0; i < len; i++) {
                cache.addLast(read4Data[i]);
            }
        }

        int cacheLen = cache.size();

        for (int i = 0; i < Math.min(n, cacheLen); i++) {
            buf[i] = (char) cache.pollFirst();
        }

        return Math.min(n, cacheLen);
    }
}
