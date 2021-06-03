package mock_state.leetc359_e;

import java.util.*;

public class Logger {

    private Map<String,Integer> logPrinter;
    /** Initialize your data structure here. */
    public Logger() {
        this.logPrinter = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(logPrinter.containsKey(message)){
            int nextTimestamp = logPrinter.get(message);
            if(nextTimestamp<=timestamp){
                logPrinter.put(message,timestamp+10);
                return true;
            } else{
                return false;
            }
        }
        logPrinter.put(message,timestamp+10);
        return true;
    }
}
