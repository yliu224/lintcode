package dfs.leetc426_m;

import datastructure.Node;

class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null)
            return null;
        DFS(root, true);
        Node tail = root;
        Node head = root;
        while (head.left != null) {
            head = head.left;
        }
        while (tail.right != null) {
            tail = tail.right;
        }

        head.left = tail;
        tail.right = head;

        return head;
    }

    public Node DFS(Node node, boolean isLeft) {
        if (node.left == null && node.right == null) {
            return node;
        }
        Node leftNode = null, rightNode = null;
        if (node.left != null) {
            leftNode = DFS(node.left, true);
            leftNode.right = node;
            node.left = leftNode;
        }

        if (node.right != null) {
            rightNode = DFS(node.right, false);
            rightNode.left = node;
            node.right = rightNode;
        }

        // 注意这个地方，返回的是最右边或最左边的Node
        if (isLeft) {
            if (rightNode == null) {
                return node;
            } else {
                while (rightNode.right != null) {
                    rightNode = rightNode.right;
                }
                return rightNode;
            }
        } else {
            if (leftNode == null) {
                return node;
            } else {
                while (leftNode.left != null) {
                    leftNode = leftNode.left;
                }
                return leftNode;
            }
        }

    }
}
