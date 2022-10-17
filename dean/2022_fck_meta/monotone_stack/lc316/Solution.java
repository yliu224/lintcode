package monotone_stack.lc316;

import java.util.*;

public class Solution {
    //1. 想清楚Stack的定义，这儿的Stack里面存的就是答案
    //2. 注意入Stack 和出Stack的条件
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> cMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            cMap.put(s.charAt(i), cMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        Stack<Character> stack = new Stack<>();
        Set<Character> inStack = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(inStack.contains(c)){
                cMap.put(c, cMap.get(c) - 1);
                continue;
            }
            while (!stack.isEmpty() && c <= stack.peek()) {
                char top = stack.peek();
                if (cMap.get(top) > 1) {
                    stack.pop();
                    inStack.remove(top);
                    cMap.put(top, cMap.get(top) - 1);
                } else{
                    break;
                }
            }
            stack.push(c);
            inStack.add(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0,stack.pop());
        }
        return sb.toString();
    }
}
