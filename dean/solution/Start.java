import bfs.lc178_m.*;

public class Start {
    public static void main(String[] args){
        Solution s = new Solution();

        //System.out.println(s.lakeEscape(4,new int[][]{{1,1,1,1},{1,-1,-1,1},{-1,1,1,-1},{1,-1,-1,1}},2,1,2,2));
        //s.shortestPathLength(new int[][]{{1,2,3},{0},{0},{0}});
        System.out.println(s.slidingPuzzle(new int[][]{{3,2,4},{1,5,0}}));
    }
}
