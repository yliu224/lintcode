package sort.leetc692_m;

import java.util.*;

public class Solution {
    class WordCount {
        public int count;
        public String word;

        public WordCount(String word) {
            this.word = word;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        PriorityQueue<WordCount> pq = new PriorityQueue<>((a, b) -> {
            if (a.count == b.count) {
                return a.word.compareTo(b.word);
            }
            return b.count - a.count;
        });

        Map<String, WordCount> record = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            WordCount w = record.getOrDefault(words[i], new WordCount(words[i]));
            w.count++;
            record.put(words[i], w);
        }

        for (Map.Entry<String, WordCount> e : record.entrySet()) {
            pq.add(e.getValue());
        }

        List<String> result = new ArrayList<>();
        int len = pq.size();// 注意这儿len可能会变！！！不能写成Math.min(k, pq.size());
        for (int i = 0; i < Math.min(k, len); i++) {
            result.add(pq.poll().word);
        }

        return result;
    }
}
