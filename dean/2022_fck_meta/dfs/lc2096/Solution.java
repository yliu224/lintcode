package dfs.lc2096;

import datastructure.TreeNode;

public class Solution {
    StringBuilder startPath=null,endPath=null;
    public String getDirections(TreeNode root, int startValue, int destValue) {
        DFS(root,startValue,destValue,new StringBuilder());
        //注意这个len是一直在变的
        int len = Math.min(startPath.length(),endPath.length());
        for(int i=0;i<len;i++){
            //注意这儿一定是 0
            if(startPath.charAt(0)==endPath.charAt(0)){
                startPath.deleteCharAt(0);
                endPath.deleteCharAt(0);
            } else{
                //要记得break
                break;
            }
        }
        for(int i=0;i<startPath.length();i++){
            endPath.insert(0,"U");
        }
        return endPath.toString();
    }
    private void DFS(TreeNode node,int start,int end,StringBuilder path){
        if(startPath!=null && endPath!=null){
            return;
        }
        if(node.val==start){
            startPath = new StringBuilder(path.toString());
        }
        if(node.val==end){
            endPath = new StringBuilder(path.toString());
        }
        if(node.left!=null){
            path.append("L");
            DFS(node.left,start,end,path);
            path.deleteCharAt(path.length()-1);
        }
        if(node.right!=null){
            path.append("R");
            DFS(node.right,start,end,path);
            path.deleteCharAt(path.length()-1);
        }
    }
}
