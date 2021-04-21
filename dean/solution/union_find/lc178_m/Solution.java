package union_find.lc178_m;

import java.util.HashMap;
import java.util.Map;

//并查集
public class Solution {
    Map<Integer,Integer> union = new HashMap<>();
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        if(n!=edges.length+1){
            return false;
        }

        for(int i=0;i<edges.length;i++){
            //在形成树之前，点应该都是独立的 !!!
            if(compressed_find(edges[i][0]) ==compressed_find(edges[i][1])){
                return false;
            }
            union(edges[i][0],edges[i][1]);
        }
        return true;
    }

    private void union(int x,int y){
        int p1 = compressed_find(x);
        int p2 = compressed_find(y);

        if(p1!=p2){
            union.put(p1,p2);
        }
    }

    private int compressed_find(int x){
        int root = x;
        while(union.get(root)!=null){
            root = union.get(root);
        }

        while(x!=root){
            int orginalFather = union.get(x);
            union.put(x,root);
            x = orginalFather;
        }

        return root;
    }
}
