package dfs.lc545;

import datastructure.TreeNode;

import java.util.*;

public class Solution {
    List<Integer> result = new ArrayList<>();

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        result.add(root.val);
        DFSLeft(root.left, true);
        //System.out.println(result);
        DFSRight(root.right, true);
        //System.out.println(result);
        return result;
    }

    void DFSRight(TreeNode node, boolean take) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            result.add(node.val);
            return;
        }
        if (node.left != null) {
            DFSRight(node.left, node.right == null && take);
        }
        if (node.right != null) {
            DFSRight(node.right, take);
        }
        if (take) {
            result.add(node.val);
        }
    }

    void DFSLeft(TreeNode node, boolean take) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            result.add(node.val);
            return;
        }
        if (take) {
            result.add(node.val);
        }
        if (node.left != null) {
            DFSLeft(node.left, take);
            take = false;
        }
        if (node.right != null) {
            DFSLeft(node.right, take);
        }
    }
}