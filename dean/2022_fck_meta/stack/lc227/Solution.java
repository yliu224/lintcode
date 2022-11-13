package stack.lc227;

import java.util.*;

public class Solution {
    public int calculate(String s) {
        //注意这儿是Deque
        Deque<Character> op = new LinkedList<>();
        Deque<Integer> num = new LinkedList<>();
        int number=0;
        for(char c:s.toCharArray()){
            if(c==' '){
                continue;
            }
            if(c>='0' && c<='9'){
                number=number*10+(c-'0');
            } else{
                //这儿是每一个数字完了以后，再push
                if(!op.isEmpty() && (op.peekLast()=='*' || op.peekLast()=='/')){
                    int ans = calculate(num.pollLast(),number,op.pollLast());
                    num.addLast(ans);
                } else{
                    num.addLast(number);
                }
                op.addLast(c);
                number=0;
            }
        }
        if(!op.isEmpty() && (op.peekLast()=='*' || op.peekLast()=='/')){
            int ans = calculate(num.pollLast(),number,op.pollLast());
            num.addLast(ans);
        } else{
            num.addLast(number);
        }
        //最后是要正着算
        while(!op.isEmpty()){
            int a = num.pollFirst();
            int b = num.pollFirst();
            int ans = calculate(a,b,op.pollFirst());
            num.addFirst(ans);
        }
        return num.pop();
    }

    private int calculate(int a,int b,char c){
        int ans = 0;
        switch(c){
            case '+' -> ans=a+b;
            case '-' -> ans=a-b;
            case '/' -> ans=a/b;
            case '*' -> ans=a*b;
        }
        return ans;
    }
}
