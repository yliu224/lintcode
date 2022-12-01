package bfs.lc297;

import datastructure.TreeNode;

import java.util.*;

public class Solution {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        List<String> serialize = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        serialize.add(String.valueOf(root.val));
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.left != null) {
                serialize.add(String.valueOf(cur.left.val));
                q.offer(cur.left);
            } else {
                serialize.add("null");
            }
            if (cur.right != null) {
                serialize.add(String.valueOf(cur.right.val));
                q.offer(cur.right);
            } else {
                serialize.add("null");
            }
        }
        return serialize.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        if (data.equals("")) {
            return null;
        }
        String[] nodes = data.substring(1, data.length() - 1).split(",");
        if (nodes.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int index = 1;
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            String left = nodes[index++].trim();
            String right = nodes[index++].trim();
            if (!left.equals("null")) {
                cur.left = new TreeNode(Integer.parseInt(left));
                q.offer(cur.left);
            }
            if (!right.equals("null")) {
                cur.right = new TreeNode(Integer.parseInt(right));
                q.offer(cur.right);
            }
        }
        return root;
    }
}
