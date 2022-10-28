package string.lc2075;

public class Solution {
    private int jlen;
    private int ilen;

    public String decodeCiphertext(String encodedText, int rows) {
        jlen = encodedText.length() / rows;
        ilen = rows;

        char[][] chars = new char[ilen][jlen];
        for (int i = 0; i < encodedText.length(); i++) {
            int iindex = i / jlen;
            int jindex = i % jlen;
            chars[iindex][jindex] = encodedText.charAt(i);
        }

        return readString(chars);
    }

    private String readString(char[][] chars) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0, jstart = 0;
        while (i < ilen && j < jlen) {
            sb.append(chars[i][j]);
            int[] next = move(i, j, jstart);
            i = next[0];
            j = next[1];
            if (i == 0) {
                jstart++;
            }
        }
        return sb.toString().stripTrailing();
    }

    private int[] move(int i, int j, int jstart) {
        if (i < ilen - 1 && j < jlen - 1) {
            i++;
            j++;
            return new int[]{i, j};
        }

        return new int[]{0, jstart + 1};
    }
}
