package union_find.lc805_e;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
    /**
     * @param ListA: The relation between ListB's books
     * @param ListB: The relation between ListA's books
     * @return: The answer
     */
    private Map<String,String> set = new HashMap<>();
    private Map<String,Integer> setSize = new HashMap<>();
    public List<String> maximumAssociationSet(String[] ListA, String[] ListB) {
        if(ListA.length==0) return new ArrayList<>();
        for(int i=0;i<ListA.length;i++){
            merge(ListA[i],ListB[i]);
        }

        int max = Integer.MIN_VALUE;
        String maxKey=null;
        for(String key:setSize.keySet()){
            if(setSize.get(key)>max){
                maxKey = key;
                max = setSize.get(key);
            }
        }

        Set<String> result = new HashSet<>();
        for(int i=0;i<ListA.length;i++){
            if(compressed_find(ListA[i]).equals(maxKey)){
                result.add(ListA[i]);
            }
        }

        for(int i=0;i<ListB.length;i++){
            if(compressed_find(ListB[i]).equals(maxKey)){
                result.add(ListB[i]);
            }
        }
        result.add(maxKey);
        return new ArrayList<String>(result);
    }

    private void merge(String b1,String b2){
        String rootB1 = compressed_find(b1);
        String rootB2 = compressed_find(b2);

        if(!rootB1.equals(rootB2)){
            set.put(rootB1,rootB2);
            setSize.put(rootB2,setSize.getOrDefault(rootB1,1)+setSize.getOrDefault(rootB2,1));
            setSize.remove(rootB1);
        }
    }

    private String compressed_find(String book){
        String root = book;
        while(set.get(root)!=null){
            root = set.get(root);
        }

        while(set.get(book)!=null){
            String tmp = set.get(book);
            set.put(book,root);
            book=tmp;
        }

        return root;
    }
}
