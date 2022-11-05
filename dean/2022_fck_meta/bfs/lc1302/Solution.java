package bfs.lc1302;

import datastructure.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<TreeNode> swap = new ArrayDeque<>();
        int sum = 0;
        q.offer(root);
        while (!q.isEmpty()) {
            sum = 0;
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                sum += cur.val;
                if (cur.left != null) {
                    swap.add(cur.left);
                }
                if (cur.right != null) {
                    swap.add(cur.right);
                }
            }
            q.addAll(swap);
            swap.clear();
        }
        return sum;
    }
}
