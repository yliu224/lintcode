package mock_state.leetc68_m;

import java.util.*;

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> output = new ArrayList<>();
        int left = 0, right;
        while (left < words.length) {
            right = getRightIndex(left, words, maxWidth);
            List<StringBuilder> line = getLine(left, right, words);
            output.add(printLine(line, maxWidth, right == words.length - 1));
            left = right;
            left++;
        }

        return output;
    }

    private int getRightIndex(int left, String[] words, int maxWidth) {
        int len = words[left].length();
        left++;
        while (len < maxWidth && left < words.length) {
            len += words[left].length() + 1;
            left++;
        }

        //注意这儿的处理,不同情况下处理出来的word是不一样的 
        if (len > maxWidth) {
            left -= 2;
        } else if (left == words.length) {
            left--;
        } else if (len == maxWidth) {
            left--;
        }
        return left;
    }

    private List<StringBuilder> getLine(int left, int right, String[] words) {
        List<StringBuilder> wordList = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            wordList.add(new StringBuilder(words[i]));
        }
        return wordList;
    }

    private String printLine(List<StringBuilder> line, int maxWidth, boolean isLastLine) {
        StringBuilder sb = new StringBuilder();
        int leftSpace = maxWidth;

        // Print Last line
        if (isLastLine) {
            for (int i = 0; i < line.size() - 1; i++) {
                StringBuilder word = line.get(i);
                word.append(' ');
                sb.append(word);
                leftSpace -= word.length();
            }
            sb.append(line.get(line.size() - 1).toString());
            leftSpace -= line.get(line.size() - 1).length();
            for (int i = 0; i < leftSpace; i++) {
                sb.append(' ');
            }
            return sb.toString();
        }

        // print other lines
        for (int i = 0; i < line.size(); i++) {
            leftSpace -= line.get(i).length();
        }
        while (leftSpace > 0) {
            for (int i = 0; i < (line.size() > 1 ? line.size() - 1 : line.size()); i++) {
                StringBuilder w = line.get(i);
                w.append(" ");
                leftSpace--;
                if (leftSpace == 0) {
                    break;
                }
            }
        }

        for (int i = 0; i < line.size(); i++) {
            sb.append(line.get(i));
        }

        return sb.toString();
    }
}
