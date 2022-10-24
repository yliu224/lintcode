package two_pointer.lc243;

public class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int w1 = findNext(wordsDict, 0, word1);
        int w2 = findNext(wordsDict, 0, word2);
        int min = Integer.MAX_VALUE;
        while (w1 < wordsDict.length && w2 < wordsDict.length) {
            min = Math.min(Math.abs(w1 - w2), min);
            if (w1 > w2) {
                //注意这儿+1的细节
                w2 = findNext(wordsDict, w2 + 1, word2);
            } else {
                w1 = findNext(wordsDict, w1 + 1, word1);
            }
        }
        return min;
    }

    private int findNext(String[] dict, int start, String target) {
        for (int i = start; i < dict.length; i++) {
            if (dict[i].equals(target)) {
                return i;
            }
        }
        return dict.length;
    }
}
