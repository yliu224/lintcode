package tree.lc993;

import datastructure.TreeNode;

public class Solution {
    private int xd = 0, yd = 0;
    private int x, y;

    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        TreeNode xr = x == root.val ? null : findParent(root, x, 0);
        TreeNode yr = y == root.val ? null : findParent(root, y, 0);

        return xd == yd && xr != yr;
    }

    private TreeNode findParent(TreeNode n, int t, int depth) {
        if (n.left == null && n.right == null) {
            return null;
        }
        TreeNode l = null, r = null;
        if (n.left != null) {
            if (n.left.val == t && t == x) {
                xd = depth + 1;
                return n;
            }
            if (n.left.val == t && t == y) {
                yd = depth + 1;
                return n;
            }
            l = findParent(n.left, t, depth + 1);
        }

        if (n.right != null) {
            if (n.right.val == t && t == x) {
                xd = depth + 1;
                return n;
            }
            if (n.right.val == t && t == y) {
                yd = depth + 1;
                return n;
            }
            r = findParent(n.right, t, depth + 1);
        }
        if (l != null) {
            return l;
        }
        return r;
    }
}
