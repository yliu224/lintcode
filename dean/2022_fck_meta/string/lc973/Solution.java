package string.lc973;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



public class Solution {
    List<Log> digitalLogs = new ArrayList<>();
    List<Log> letterLogs = new ArrayList<>();

    public String[] reorderLogFiles(String[] logs) {
        extractLogs(logs);
        sortLetterLogs(letterLogs);
        letterLogs.addAll(digitalLogs);
        return letterLogs.stream().map(Log::toString).toArray(String[]::new);
    }

    private void extractLogs(String[] logs) {
        Arrays.stream(logs).forEach(str -> {
            Log log = Log.createLog(str);
            if (log.isDigitType()) {
                digitalLogs.add(log);
            } else {
                letterLogs.add(log);
            }
        });
    }

    private void sortLetterLogs(List<Log> letterLogs) {
        Collections.sort(letterLogs);
    }

    static class Log implements Comparable<Log> {
        String id;
        String content;

        private Log(String id, String content) {
            this.id = id;
            this.content = content;
        }

        static Log createLog(String str) {
            String id = str.split(" ")[0];
            //注意 string 的长度
            String content = str.substring(id.length()+1);
            return new Log(id, content);
        }

        boolean isDigitType() {
            return Character.isDigit(content.charAt(0));
        }

        @Override
        public String toString() {
            return id + " " + content;
        }

        @Override
        public int compareTo(Log o) {
            if (o.content.equals(this.content)) {
                return this.id.compareTo(o.id);
            }
            return this.content.compareTo(o.content);
        }
    }
}

