package dfs.lc341;

import java.util.*;

public class Solution implements Iterator<Integer> {
     interface NestedInteger {
         boolean isInteger();
         Integer getInteger();
         List<NestedInteger> getList();
     }
    private List<Integer> flattened = new ArrayList<>();
    int index = 0;
    public Solution(List<NestedInteger> nestedList) {
        flatNested(nestedList);
    }

    private void flatNested(List<NestedInteger> nestedList){
        for(NestedInteger ni:nestedList){
            if(ni.isInteger()){
                flattened.add(ni.getInteger());
            } else {
                flatNested(ni.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return flattened.get(index++);
    }

    @Override
    public boolean hasNext() {
        return flattened.size()>index;
    }
}
