package monotone_stack.lc739;

import java.util.Stack;

public class Solution {

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> s = new Stack<>();
        int[] ans = new int[temperatures.length];
        for(int i=0;i<ans.length;i++){
            ans[i]=i;
        }
        for(int i=0;i<temperatures.length;i++){
            while(!s.isEmpty() && temperatures[s.peek()]<temperatures[i]){
                int index = s.pop();
                ans[index]=i;
            }
            s.push(i);
        }
        for(int i=0;i<ans.length;i++){
            ans[i]=ans[i]-i;
        }
        return ans;
    }
}
