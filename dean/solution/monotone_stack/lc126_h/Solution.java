package monotone_stack.lc126_h;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import datastructure.TreeNode;

public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    private Map<Integer,TreeNode> tree = new HashMap<>();
    public TreeNode maxTree(int[] A) {
        // write your code here
        int max = -1;
        Stack<Integer> stack = new Stack<>();//Stack里面存的是index 
        for(int i=0;i<A.length;i++){
            max = Math.max(max,A[i]);
            while(!stack.isEmpty() && A[stack.peek()]<A[i]){
                int top = stack.pop();
                TreeNode topNode = getTreeNode(A[top]);
                TreeNode currentNode = getTreeNode(A[i]);
                int leftValue = currentNode.left == null?-1:currentNode.left.val;
                if(topNode.val>leftValue){
                    currentNode.left = topNode;
                }
            }
            stack.push(i);
        }

        stack.clear();
        for(int i=A.length-1;i>=0;i--){
            while(!stack.isEmpty() && A[stack.peek()]<A[i]){
                int top = stack.pop();
                TreeNode topNode = getTreeNode(A[top]);//注意这儿一定要是A[top]
                TreeNode currentNode = getTreeNode(A[i]);//注意这儿一定要是A[i]
                int rightValue = currentNode.right == null?-1:currentNode.right.val;
                if(topNode.val>rightValue){
                    currentNode.right = topNode;
                }
            }
            stack.push(i);
        }

        return getTreeNode(max);
    }

    private TreeNode getTreeNode(int node){
        if(!tree.containsKey(node)){
            tree.put(node,new TreeNode(node));
        }
        return tree.get(node);
    }
}