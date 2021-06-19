package string.leetc394_m;

import java.util.*;

public class Solution {
    public String decodeString(String s) {
        Stack<Integer> numbers = new Stack<>();
        Stack<StringBuilder> words = new Stack<>();
        
        StringBuilder result = new StringBuilder();
        
        int number = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='a' && s.charAt(i)<='z'){
                if(!numbers.isEmpty()){
                    StringBuilder word = words.pop();
                    word.append(s.charAt(i));
                    words.push(word);
                } else {
                    result.append(s.charAt(i));
                }
            } else if(s.charAt(i)>='0' && s.charAt(i)<='9'){
                number = number*10+Integer.parseInt(String.valueOf(s.charAt(i)));
            } else if(s.charAt(i)=='['){
                //注意这儿入栈的东西，是两个一起入栈
                numbers.push(number);
                words.push(new StringBuilder());
                number = 0;
            } else if(s.charAt(i)==']'){
                int topNum = numbers.pop();
                StringBuilder topSb = words.pop();
                StringBuilder newTopSb = new StringBuilder();
                for(int j=0;j<topNum;j++){
                    newTopSb.append(topSb);
                }
                
                if(!words.isEmpty()){
                    StringBuilder word = words.pop();
                    word.append(newTopSb);
                    words.push(word);
                } else{
                    result.append(newTopSb);
                }
            }
        }
        
        return result.toString();
    }
}
