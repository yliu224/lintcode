package mock_state.leetc1650_e;

import java.util.*;

class Solution {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> visited = new HashSet<>();
        
        Node node = p;
        visited.add(node);
        while(node.parent!=null){
            node = node.parent;
            visited.add(node);
        }
        
        node = q;
        while(!visited.contains(node)){
            node = node.parent;
        }
        
        return node;
    }
}
