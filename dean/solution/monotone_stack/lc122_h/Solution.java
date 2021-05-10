package monotone_stack.lc122_h;

import java.util.Stack;

public class Solution {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] heights) {
        // write your code here
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<=heights.length;i++){
            int value = i==heights.length?-1:heights[i];
            while(!stack.isEmpty() && !isValid(heights[stack.peek()],value)){
                int current = stack.pop();
                int left = stack.isEmpty()?-1:stack.peek();//stack为空的处理
                int width = i-left-1; //以自己为高度，向左找到最第一个比我矮的，向右找到一个比我矮的
                max = Math.max(heights[current]*width,max);
            }
            stack.push(i);
        }

        return max;
    }

    //注意这儿是两个valude的比值 
    private boolean isValid(int stackValue,int height){
        return stackValue<height;
    }
}
