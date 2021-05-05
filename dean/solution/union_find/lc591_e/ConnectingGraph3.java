package union_find.lc591_e;

import java.util.HashMap;
import java.util.Map;

public class ConnectingGraph3 {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    private Map<Integer,Integer> set = new HashMap<>();
    private Map<Integer,Integer> setSize = new HashMap<>();
    public ConnectingGraph3(int n) {
        for(int i=1;i<=n;i++){
            setSize.put(i,1);
        }
    }
    
    public void connect(int a, int b) {
        int rootA = compressed_find(a);
        int rootB = compressed_find(b);

        if(rootA!=rootB){
            set.put(rootA,rootB);
            setSize.put(rootB,setSize.getOrDefault(rootA,1)+setSize.getOrDefault(rootB,1));
            setSize.remove(rootA);
        }
    }

    /**
     * @return: An integer
     */
    public int query() {
        return setSize.entrySet().size();
    }

    private int compressed_find(int x){
        int root = x;
        while(set.get(root)!=null){
            root = set.get(root);
        }

        while(set.get(x)!=null){
            int tmp =set.get(x);
            set.put(x,root);
            x = tmp;
        }
        return root;
    }
}
