package string.leetc418_e;

//一定要把过程抽象出来，然后写大框架，然后再写小函数
public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int round = 0;
        int sentenceIndex = 0;
        for (int i = 0; i < rows; i++) {
            int j = 0;
            while (canPrint(sentence[sentenceIndex], j, cols)) {
                j = j + sentence[sentenceIndex].length() + 1;

                sentenceIndex++;
                if (sentenceIndex == sentence.length) {
                    sentenceIndex = 0;
                    round++;
                }
            }
        }

        return round;
    }

    private boolean canPrint(String str, int j, int cols) {
        if (j + str.length() <= cols) {
            return true;
        } else {
            return false;
        }
    }
}