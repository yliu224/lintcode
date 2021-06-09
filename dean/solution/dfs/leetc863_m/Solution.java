package dfs.leetc863_m;

import java.util.*;
import java.util.stream.Collectors;

import datastructure.TreeNode;

public class Solution {
    private int path2Target = -1;
    private List<TreeNode> targetPath;
    private List<String> targetDirection;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findTargetLength(root, target, 0, new Stack<>(), new Stack<>());
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < targetPath.size(); i++) {
            TreeNode node = targetPath.get(i);
            String dir = targetDirection.get(i);
            if (k > path2Target) {
                result.addAll(findNodes(dir.equals("L") ? node.right : node.left, k - path2Target - 1));
            }
            if (k == path2Target) {
                result.add(node.val);
            }
            path2Target--;
        }
        result.addAll(findNodes(target, k));

        return result;
    }

    private List<Integer> findNodes(TreeNode root, int distance) {
        if (root == null)
            return new ArrayList<>();
        if (distance == 0)
            return new ArrayList<>(Arrays.asList(root.val));
        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> cacheQ = new LinkedList<>();
        q.add(root);

        int steps = 0;
        while (!q.isEmpty()) {
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    cacheQ.add(node.left);
                }
                if (node.right != null) {
                    cacheQ.add(node.right);
                }
            }
            q.addAll(cacheQ);
            cacheQ.clear();
            steps++;
            if (steps == distance)
                break;
        }

        List<Integer> result = new ArrayList<>();
        if (steps == distance) {
            for (TreeNode n : q) {
                result.add(n.val);
            }
        }
        return result;

    }

    private void findTargetLength(TreeNode node, TreeNode target, int distance, Stack<TreeNode> path,
            Stack<String> direction) {
        if (node == null)
            return;
        if (path2Target != -1)
            return;
        if (node == target) {
            path2Target = distance;
            //记住如何把Collection转换成List
            targetPath = path.stream().collect(Collectors.toList());
            targetDirection = direction.stream().collect(Collectors.toList());
            return;
        }
        //学习用DFS记录路径 
        path.push(node);
        direction.push("L");
        findTargetLength(node.left, target, distance + 1, path, direction);
        direction.pop();
        direction.push("R");
        findTargetLength(node.right, target, distance + 1, path, direction);
        direction.pop();
        path.pop();
    }
}
