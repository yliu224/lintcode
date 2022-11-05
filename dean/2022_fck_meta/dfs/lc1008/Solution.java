package dfs.lc1008;

import datastructure.TreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        List<Integer> preorderList = Arrays.stream(preorder).boxed().toList();
        TreeNode root = new TreeNode(preorder[0]);
        return DFS(root,preorderList.stream().filter(x->x<root.val).toList(),preorderList.stream().filter(x->x>root.val).toList());
    }

    private TreeNode DFS(TreeNode node, List<Integer> left, List<Integer> right) {
        if(left.isEmpty() && right.isEmpty()){
            return node;
        }
        if(!left.isEmpty()){
            TreeNode leftNode = new TreeNode(left.get(0));
            node.left = DFS(leftNode,left.stream().filter(x->x<leftNode.val).toList(),left.stream().filter(x->x>leftNode.val).toList());
        }
        if(!right.isEmpty()){
            TreeNode rightNode = new TreeNode(right.get(0));
            node.right = DFS(rightNode,right.stream().filter(x->x<rightNode.val).toList(),right.stream().filter(x->x>rightNode.val).toList());
        }
        return node;
    }
}
