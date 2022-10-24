
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import linked_list.lc460.LFUCache;
import string.lc1152.*;

public class Start {
    public static void main(String[] args){
        LFUCache c = new LFUCache(2);
        c.put(1,1);
        c.put(2,2);
        c.get(1);
        c.put(3,3);
        c.get(2);
    }
}
