package mock_state.lc380;

import java.util.*;

public class Solution {
    private Map<Integer,Integer> index;
    private List<Integer> list;
    public Solution() {
        this.index = new HashMap<>();
        this.list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if(!index.containsKey(val)){
            list.add(val);
            index.put(val,list.size()-1);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if(index.containsKey(val)){
            int i = index.get(val);
            list.remove(i);
            index.remove(val);
            //注意这儿特殊值的判断
            if(list.size()==0 || i==list.size()){
                return true;
            }
            int tmp = list.get(list.size()-1);
            list.add(i,tmp);
            list.remove(list.size()-1);
            index.put(tmp,i);
            return true;
        }
        return false;
    }

    public int getRandom() {
        Random r = new Random();
        return list.get(r.nextInt(list.size()));
    }
}

