import bfs.lc1469_m.*;

public class Start {
    public static void main(String[] args){
        Solution_BFS s = new Solution_BFS();

        //System.out.println(s.lakeEscape(4,new int[][]{{1,1,1,1},{1,-1,-1,1},{-1,1,1,-1},{1,-1,-1,1}},2,1,2,2));
        //s.shortestPathLength(new int[][]{{1,2,3},{0},{0},{0}});
        System.out.println(s.longestPath(5, new int[]{0,0,2,2},new int[]{1,2,3,4},new int[]{1,2,5,6}));
    }
}
