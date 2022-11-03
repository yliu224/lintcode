package datastructure;

public class Node {
    public int val;
    public Node left, right;
    public Node next;
    public Node random;
    public Node(int val) {
        this.val = val;
        this.left = this.right = null;
    }

}
