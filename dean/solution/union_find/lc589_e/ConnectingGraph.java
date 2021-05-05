package union_find.lc589_e;

import java.util.HashMap;
import java.util.Map;

public class ConnectingGraph {
    private Map<Integer,Integer> set = new HashMap<>();
    /*
    * @param n: An integer
    */public ConnectingGraph(int n) {
        // do intialization if necessary
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        int rootA = compressed_find(a);
        int rootB = compressed_find(b);

        if(rootA!=rootB){
            set.put(rootA,rootB);
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        return compressed_find(a)==compressed_find(b);
    }


    private int compressed_find(int x){
        int root = x;
        while(set.get(root)!=null){
            root=set.get(root);
        }

        while(set.get(x)!=null){
            int tmp = set.get(x);
            set.put(x,root);
            x=tmp;
        }
        return root;
    }
}
