package binary_search.leetc938_e;

import java.util.*;

import datastructure.TreeNode;

class Solution {
    private int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        Queue<TreeNode> q = new LinkedList<>();
        q = addWhich(low, high, root, q);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            q = addWhich(low, high, node, q);
        }

        return sum;

    }

    private Queue<TreeNode> addWhich(int low, int high, TreeNode node, Queue<TreeNode> q) {
        if (node.val >= low && node.val <= high) {
            sum += node.val;
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        } else if (node.val < low) {
            if (node.right != null) {
                q.add(node.right);
            }
        } else if (node.val > high) {
            if (node.left != null) {
                q.add(node.left);
            }
        }

        return q;
    }
}
