package dfs.leetc301_m;

import java.util.*;

class Solution {
    
    private Set<String> matchedString;
    public List<String> removeInvalidParentheses(String s) {
        matchedString = new HashSet<>();
        for(int i=0;i<s.length();i++){
            DFS(s.substring(i),new StringBuilder(),new Stack<>());
        }

        return findLongestMatched();
    }
    
    private List<String> findLongestMatched(){
        List<String> results = new ArrayList<>();
        int maxLen = 0;
        for(String ms:matchedString){
            maxLen = Math.max(maxLen,ms.length());
        }
        
        for(String ms:matchedString){
            if(ms.length()==maxLen){
                results.add(ms);
            }
        }
        
        return results;
    }
    
    private void DFS(String tobeMatched,StringBuilder matched, Stack<Character> stack){   
        if(stack.isEmpty()){
            //System.out.println(matched.toString());
            matchedString.add(String.valueOf(matched.toString()));
        }
        if(tobeMatched.equals("")){
            return;
        }
        //take the top from toBeMatched
        char top = tobeMatched.charAt(0);
        matched.append(top);
        if(top==')'&&!stack.isEmpty()){
            char topStack = stack.pop();
            DFS(tobeMatched.substring(1),matched,stack);
            stack.push(topStack);
        } else if(top=='('){
            stack.push('(');
            DFS(tobeMatched.substring(1),matched,stack);
            stack.pop();
        } else if(top!=')'&&top!='(') {
            DFS(tobeMatched.substring(1),matched,stack);
        }
        matched.deleteCharAt(matched.length()-1);
        
        //skip the top of toBeMatched
        DFS(tobeMatched.substring(1),matched,stack);
    }
}
