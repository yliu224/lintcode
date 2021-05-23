package string.leetc1249_m;

import java.util.*;

class Solution {
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> paretheses = new LinkedList<>();
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(' || s.charAt(i)==')'){
                if(isMatch(s.charAt(i),paretheses,s)){
                    paretheses.removeLast();
                }else{
                    paretheses.addLast(i);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(!paretheses.isEmpty() && i==paretheses.getFirst()){
                paretheses.removeFirst();
                continue;
            }
            sb.append(s.charAt(i));
        }
        
        return sb.toString();
    }
    
    private boolean isMatch(char c,Deque<Integer> q,String s){
        if(q.isEmpty()){
            return false;
        }
        
        if(c==')' && s.charAt(q.getLast())=='('){
            return true;
        }
        return false;
    }
}
