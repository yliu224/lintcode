package dfs.leetc1740_h;

import java.util.*;
import java.util.stream.Collectors;

import datastructure.TreeNode;

public class Solution {
    private List<Integer> tmpPath;
    private boolean isFound;

    public int findDistance(TreeNode root, int p, int q) {
        isFound = false;
        findPath(root, new Stack<>(), p);
        List<Integer> pPath = new ArrayList<>(tmpPath);

        isFound = false;
        findPath(root, new Stack<>(), q);
        List<Integer> qPath = new ArrayList<>(tmpPath);

        int minLen = Math.min(pPath.size(), qPath.size());

        if (minLen != 1) {
            int lca = 0;
            for (int i = 0; i < minLen; i++) {
                lca = i;
                if (i + 1 < minLen) {
                    if (!pPath.get(i + 1).equals(qPath.get(i + 1))) {// 注意这儿比较的时候用equals！！！
                        break;
                    }
                }
            }
            lca++;
            return pPath.size() - lca + qPath.size() - lca;
        } else {
            return Math.max(pPath.size(), qPath.size()) - 1;
        }
    }

    private void findPath(TreeNode node, Stack<TreeNode> path, int k) {
        if (isFound)
            return;
        if (node.val == k) {
            isFound = true;
            tmpPath = path.stream().map(n -> n.val).collect(Collectors.toList());
            tmpPath.add(node.val);
            return;
        }
        if (node.left == null && node.right == null) {// 注意这儿用&&
            return;
        }

        path.push(node);
        if (node.left != null) {
            findPath(node.left, path, k);
        }
        if (node.right != null) {
            findPath(node.right, path, k);
        }
        path.pop();

    }
}
