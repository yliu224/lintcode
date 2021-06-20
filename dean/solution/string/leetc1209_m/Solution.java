package string.leetc1209_m;

import java.util.Stack;

public class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Integer> counts = new Stack<>();
        Stack<Character> ch = new Stack<>();
        
        for(int i=0;i<s.length();i++){
            if(!ch.isEmpty() && ch.peek()==s.charAt(i)){
                int count = counts.pop();
                count++;
                if(count==k){
                    ch.pop();
                } else{
                    counts.push(count);
                }
            } else{
                ch.push(s.charAt(i));
                counts.push(1);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(!ch.isEmpty()){
            int count = counts.pop();
            char c = ch.pop();
            for(int i=0;i<count;i++){
                sb.insert(0,c);
            }
        }
        
        return sb.toString();
    }
}
