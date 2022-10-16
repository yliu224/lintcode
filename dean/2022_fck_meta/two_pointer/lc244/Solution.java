package two_pointer.lc244;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Map<String, List<Integer>> dict = new HashMap<>();

    public Solution(String[] wordsDict) {
        for (int i = 0; i < wordsDict.length; i++) {
            dict.computeIfAbsent(wordsDict[i], s -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> word1Index = dict.get(word1);
        List<Integer> word2Index = dict.get(word2);

        int i1 = 0, i2 = 0;
        int min = Integer.MAX_VALUE;
        while (i1 < word1Index.size() || i2 < word2Index.size()) {
            //尽量不要去copy paste
            int index1 = word1Index.get(i1 == word1Index.size() ? i1 - 1 : i1);
            int index2 = word2Index.get(i2 == word2Index.size() ? i2 - 1 : i2);

            min = Math.min(min, Math.abs(index1 - index2));

            //注意指针移动的条件
            if (index1 < index2) {
                if (i1 < word1Index.size()) {
                    i1++;
                } else {
                    i2++;
                }
            } else {
                if (i2 < word2Index.size()) {
                    i2++;
                } else {
                    i1++;
                }

            }
        }
        return min;
    }
}
