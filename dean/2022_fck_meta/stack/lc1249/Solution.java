package stack.lc1249;

import java.util.*;

public class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> invalidP = new HashSet<>();
        for(int i=0;i<s.toCharArray().length;i++){
            char c = s.charAt(i);
            if(c=='('){
                stack.push(i);
            }
            if(c==')'){
                if(stack.isEmpty()){
                    invalidP.add(i);
                } else{
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()){
            invalidP.add(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.toCharArray().length;i++){
            if(!invalidP.contains(i)){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
