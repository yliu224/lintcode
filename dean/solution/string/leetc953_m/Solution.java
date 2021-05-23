package string.leetc953_m;

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length <= 1)
            return true;

        for (int i = 1; i < words.length; i++) {
            if (!isOrdered(words[i - 1], words[i], order)) {
                return false;
            }
        }

        return true;
    }

    private boolean isOrdered(String w1, String w2, String order) {
        for (int i = 0; i < Math.min(w1.length(), w2.length()); i++) {
            String c1 = w1.substring(i, i + 1);
            String c2 = w2.substring(i, i + 1);

            if (order.indexOf(c1) < order.indexOf(c2)) {
                return true;
            } else if (c1.equals(c2)) {
                continue;
            }
            return false;
        }

        if (w1.length() > w2.length()) {
            return false;
        }

        return true;
    }
}
