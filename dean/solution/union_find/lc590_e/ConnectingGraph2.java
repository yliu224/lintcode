package union_find.lc590_e;

import java.util.HashMap;
import java.util.Map;

public class ConnectingGraph2 {
    private Map<Integer,Integer> set = new HashMap<>();
    private Map<Integer,Integer> setSize = new HashMap<>();
    /*
    * @param n: An integer
    */public ConnectingGraph2(int n) {
        setSize.put(n,1);
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
            setSize.put(rootB,setSize.getOrDefault(rootA,1)+setSize.getOrDefault(rootB,1));
            setSize.remove(rootA);
        }
    }

    /*
     * @param a: An integer
     * @return: An integer
     */
    public int query(int a) {
        return setSize.getOrDefault(compressed_find(a),1);
    }

    private int compressed_find(int x){
        int root = x;
        while(set.get(root)!=null){
            root=set.get(root);
        }

        while(set.get(x)!=null){
            int tmp = set.get(x);
            set.put(x,root);
            x = tmp;
        }

        return root;
    }
}