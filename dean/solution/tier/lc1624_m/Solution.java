package tier.lc1624_m;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /**
     * @param s: the list of binary string
     * @return: the max distance
     */
    class TrieNode {
        public TrieNode left;//0
        public TrieNode right;//1
        public int leftMax;//表示这个节点左边儿子最长的path的长度
        public int rightMax;//表示这个节点右儿子最长的path的长度 

        public TrieNode(){
            left = null;
            right = null;
            leftMax = 0;
            rightMax = 0;
        }
    }
    public int getAns(String[] s) {
        if(s.length<=1) return 0;
        TrieNode root = new TrieNode();

        int longest = 0;
        int shortest = Integer.MAX_VALUE;

        //Build Tree
        for(int i=0;i<s.length;i++){
            char[] c=s[i].toCharArray();

            if(c.length>longest){
                longest = c.length;
            } 
            if(c.length<shortest){
                shortest = c.length;
            }


            TrieNode node = root;
            for(int j=0;j<c.length;j++){
                if(c[j]=='0'){
                    node.leftMax = Math.max(node.leftMax,c.length-j);//注意这个赋值的位置
                    if(node.left==null){
                        node.left = new TrieNode();
                    }
                    node = node.left;
                } else{
                    node.rightMax = Math.max(node.rightMax,c.length-j);//注意这个赋值的位置
                    if(node.right==null){
                        node.right = new TrieNode();
                    }
                    node = node.right;     
                }
            }
        }

        //BFS find answer
        Queue<TrieNode> q = new LinkedList<>();

        int max =Integer.MIN_VALUE;
        q.offer(root);

        while(!q.isEmpty()){
            TrieNode node = q.poll();
            if(node.left!=null && node.right!=null){
                max = Math.max(max,node.leftMax+node.rightMax);
            }
            if(node.left!=null){
                q.offer(node.left);
            }

            if(node.right!=null){
                q.offer(node.right);
            }
        }

        //特殊情况，当所有string都在一条path上 ["11","1"]
        return max==Integer.MIN_VALUE?longest-shortest:max;
    }
}
