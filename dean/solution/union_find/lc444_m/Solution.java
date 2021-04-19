package union_find.lc444_m;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private boolean isTree = true;
    private Map<Integer,Integer> unionSet = new HashMap<>();
    
    private int edges = 0;
    private int nodes = 0;
    public void addEdge(int a, int b) {
        edges++;
        if(!unionSet.containsKey(a)){
            nodes++;
        }
        if(!unionSet.containsKey(b)){
            nodes++;
        }
        int p1 = compressed_find(a);
        int p2 = compressed_find(b);
        
        if(p1==a) unionSet.put(p1,null);
        if(p2==b) unionSet.put(p2,null);
        
        if(p1==p2){
            isTree = false;
        } else{
            unionSet.put(p1,p2);
        }
    }
    
    private int compressed_find(int x){
        int root = x;
        while(unionSet.get(root)!=null){
            root = unionSet.get(root);
        }
        
        while(x!=root){
            int originalFather = unionSet.get(x);
            unionSet.put(x,root);
            x=originalFather;
        }
        
        return root;
    }

    /**
     * @return: check whether these edges make up a valid tree
     */
    public boolean isValidTree() {
        // 判断是否是一颗树的充分必要条件！！！
        if(nodes-1!=edges){
            return false;
        }
        return isTree;
    }
}
