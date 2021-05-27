package dfs.leetc636_m;

import java.util.*;

class Solution {
    private Map<Integer, Integer> timeCount = new HashMap<>();
    private int i = 0;

    public int[] exclusiveTime(int n, List<String> logs) {
        while (i < logs.size()) {
            DFS(logs);
        }

        int[] excecutionTime = new int[n];

        for (int key : timeCount.keySet()) {
            excecutionTime[key] = timeCount.get(key);
        }

        return excecutionTime;
    }

    private int DFS(List<String> logs) {
        int start = 0, end, innerTimeSpent = 0, threadId = -1;

        String log = logs.get(i);
        if (log.contains("start")) {
            threadId = getCallId(log);
            start = getTimeStamp(log);
            i++;
        }

        while (!isEnd(logs.get(i), threadId)) {
            innerTimeSpent += DFS(logs);
        }

        end = getTimeStamp(logs.get(i));
        i++;
        timeCount.put(threadId, timeCount.getOrDefault(threadId, 0) + end - start + 1 - innerTimeSpent);
        return end - start + 1;
    }

    private int getCallId(String str) {
        return Integer.parseInt(str.substring(0, str.indexOf(":")));
    }

    private int getTimeStamp(String str) {
        return Integer.parseInt(str.substring(str.lastIndexOf(":") + 1));
    }

    private boolean isEnd(String log, int threadId) {
        if (threadId == -1) {
            throw new RuntimeException(String.format("Invalid log {%s}", log));
        }
        return log.contains("end") && getCallId(log) == threadId;
    }
}
