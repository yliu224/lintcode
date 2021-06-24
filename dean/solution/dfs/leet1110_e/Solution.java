package dfs.leet1110_e;

import java.util.*;
import java.util.stream.Collectors;

import datastructure.TreeNode;

public class Solution {
    private List<TreeNode> result;
    private Set<Integer> toDeleteNodes;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null)
            return new ArrayList<>();
        result = new ArrayList<>();
        toDeleteNodes = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        DFS(root);
        if (!toDeleteNodes.contains(root.val)) {
            result.add(root);
        }
        return result;
    }

    private void DFS(TreeNode node) {
        if (node.left == null && node.right == null) {
            return;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;

        if (toDeleteNodes.contains(node.val)) {
            if (left != null && !toDeleteNodes.contains(left.val)) {
                result.add(left);
            }
            if (right != null && !toDeleteNodes.contains(right.val)) {
                result.add(right);
            }
        }

        if (left != null) {

            if (toDeleteNodes.contains(left.val)) {
                node.left = null;
            }
            DFS(left);
        }

        if (right != null) {
            if (toDeleteNodes.contains(right.val)) {
                node.right = null;
            }
            DFS(right);
        }
    }
}
