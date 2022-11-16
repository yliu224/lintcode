package sort.lc791;

import java.util.*;

public class Solution {
    public String customSortString(String order, String s) {
        Map<Character, Integer> sort = new HashMap<>();
        for (int i = 0; i < order.toCharArray().length; i++) {
            int finalI = i;
            sort.computeIfAbsent(order.charAt(i), x -> finalI);
        }
        Character[] ch = new Character[s.length()];
        for(int i=0;i<s.toCharArray().length;i++){
            ch[i]=s.charAt(i);
        }
        //sort不支持 char[] 只能是 Character[]
        Arrays.sort(ch,(a,b)->sort.getOrDefault(a,Integer.MAX_VALUE)-sort.getOrDefault(b,Integer.MAX_VALUE));
        StringBuilder sb = new StringBuilder();
        for(char c:ch){
            sb.append(c);
        }
        return sb.toString();
    }
}
