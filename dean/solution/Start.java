import java.util.Set;

import bfs.lc615_h.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Start {
    static class A {
        int index;

        public A(int index){
            this.index=index;
        }

        
    }
    public static void main(String[] args){
        Solution s = new Solution();

        //System.out.println(s.lakeEscape(4,new int[][]{{1,1,1,1},{1,-1,-1,1},{-1,1,1,-1},{1,-1,-1,1}},2,1,2,2));
        //s.shortestPathLength(new int[][]{{1,2,3},{0},{0},{0}});
        //System.out.println(s.canFinish(2, new int[][]{{1,0}}));
        List<Integer> t = new ArrayList<>(Arrays.asList(1,2,3));

        System.out.print(t.contains(2));
    }
}
