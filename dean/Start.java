
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import string.lc1152.*;

public class Start {
    public static void main(String[] args){
        Map<Character,Integer> m1 = new HashMap<>();
        Map<Character,Integer> m2 = new HashMap<>();
        m1.put('a',1);
        m2.put('a',2);
        m1.put('b',1);
        m2.put('b',2);
        m2.put('c',2);
        m2.putAll(m1);
        Solution s = new Solution();
        s.mostVisitedPattern(
                new String[]{"joe","joe","joe","james","james","james","james","mary","mary","mary"},
                new int[]{1,2,3,4,5,6,7,8,9,10},
                new String[]{"home","about","career","home","cart","maps","home","home","about","career"});
    }
}
