package dfs.leetc951_e;

import datastructure.TreeNode;

public class Solution {
    private boolean isFlipable = true;

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        DFS(root1, root2);
        return isFlipable;
    }

    private void DFS(TreeNode node1, TreeNode node2) {
        if (!isFlipable) {
            return;
        }
        if (node1 == null || node2 == null) {
            isFlipable = node1 == node2;
            return;
        }

        if (node1.val != node2.val) {
            isFlipable = false;
            return;
        } else {
            TreeNode left1 = node1.left;
            TreeNode right1 = node1.right;
            TreeNode left2 = node2.left;
            TreeNode right2 = node2.right;

            if (isFlipable(left1, right1, left2, right2)) {
                if (isNeedFilp(left1, right1, left2, right2)) {
                    node2.left = right2;
                    node2.right = left2;
                }

                DFS(node1.left, node2.left);
                DFS(node1.right, node2.right);
            } else {
                isFlipable = false;
                return;
            }
        }
    }

    private boolean equals(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }

        if (n1 != null && n2 != null && n1.val == n2.val) {
            return true;
        }

        return false;
    }

    private boolean isFlipable(TreeNode l1, TreeNode r1, TreeNode l2, TreeNode r2) {
        if (equals(l1, l2) && equals(r1, r2)) {
            return true;
        }

        if (equals(l1, r2) && equals(r1, l2)) {
            return true;
        }

        return false;
    }

    private boolean isNeedFilp(TreeNode l1, TreeNode r1, TreeNode l2, TreeNode r2) {
        return equals(l1, r2) && equals(r1, l2);
    }
}
