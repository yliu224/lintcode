package union_find.lc1396_m;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * @param sets: Initial set list
     * @return: The final number of sets
     */
    Map<Integer,Integer> set = new HashMap<>();
    Map<Integer,Integer> setSize = new HashMap<>();
    public int setUnion(int[][] sets) {
        for(int i=0;i<sets.length;i++){
            for(int j=0;j<sets[i].length;j++){
                if(j==0){
                    if(setSize.get(compressed_find(sets[i][j]))==null){//特殊处理
                        setSize.put(sets[i][j],1);
                    }
                } else{
                    merge(sets[i][0],sets[i][j]);
                }
            }
        }

        return setSize.entrySet().size();
    }

    private void merge(int x,int y){
        int rootX = compressed_find(x);
        int rootY = compressed_find(y);

        if(rootX==x){//记下来，模板化
            setSize.put(x,1);
        }
        if(rootY==y){
            setSize.put(y,1);
        }

        if(rootX!=rootY){
            set.put(rootX,rootY);
            setSize.put(rootY,setSize.get(rootX)+setSize.get(rootY));
            setSize.remove(rootX);
        }
    }

    private int compressed_find(int x){
        int root=x;
        while(set.get(root)!=null){
            root = set.get(root);
        }

        while(set.get(x)!=null){//注意这儿是!=null
            int tmpParent=set.get(x);
            set.put(x,root);
            x=tmpParent;
        }

        return root;
    }

}
