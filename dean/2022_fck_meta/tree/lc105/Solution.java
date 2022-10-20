package tree.lc105;

import datastructure.TreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return DFS(Arrays.stream(preorder).boxed().collect(Collectors.toList()),
                Arrays.stream(inorder).boxed().collect(Collectors.toList()));
    }

    private TreeNode DFS(List<Integer> preorder, List<Integer> inorder) {
        if (inorder == null || inorder.size() == 0) {
            return null;
        }
        if (inorder.size() == 1) {
            preorder.remove(0);
            return new TreeNode(inorder.get(0));
        }


        int rootN = preorder.get(0);
        TreeNode root = new TreeNode(rootN);
        List<Integer> leftTree = null, rightTree = null;
        for (int i = 0; i < inorder.size(); i++) {
            if (inorder.get(i) == rootN) {
                leftTree = i == 0 ? null : inorder.subList(0, i);
                rightTree = i == inorder.size() - 1 ? null : inorder.subList(i + 1, inorder.size());
                break;
            }
        }
        preorder.remove(0);
        root.left = DFS(preorder, leftTree);
        root.right = DFS(preorder, rightTree);
        return root;
    }
}
