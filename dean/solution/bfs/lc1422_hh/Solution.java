package bfs.lc1422_hh;

import java.util.LinkedList;
import java.util.Queue;

/**首先构建一个数据结构<node,status> status表示走到当前节点的时候，!!!整个图上的点的访问状态!!!，如果所有的点都被访问过了
*  那么，输出当前步数就是最短路径
*  1--2--3
*     |
*     4
*  第0步：---------------------------------------------------------------------------------------------------------------------
*  [1:1]                            [2:2]                           [3:3]                       [4:4]
*  第1步：---------------------------------------------------------------------------------------------------------------------
*  [2:1,2]                          [1:2,1 | 4:2,4 | 3:2,3]         [2:3,2]                     [2:4,2]
*  第2步: ---------------------------------------------------------------------------------------------------------------------
*  [1:1,2]                          [2:1,2 | 2:2,4 | 2:2,3]         [3:3,2]                     [4:4,2]
*  已经出现过，所以跳过               跳过                            跳过                         跳过 
*  [4:1,2,4 | 3:1,2,3]              []                              [1:3,2,1 | 4:3,2,4]         [1:4,2,1 | 3:4,2,3]
*  第3步: ---------------------------------------------------------------------------------------------------------------------
*  [2:1,2,4 | 2:1,2,3]                                              [2:3,2,1 | 2:3,2,1]         [2,4,2,1 | 2:4,2,3]
*  第4步: ---------------------------------------------------------------------------------------------------------------------
*  [1:1,2,4 | 4:1,2,4 | 1:1,2,3 | 3:1,2,3]
*  跳过
*  [3:1,2,4,3 | 4:1,2,3,4]//跳出循环
*  有空再来敲一遍！！！
**/
public class Solution {
    /**
     * @param graph: the graph
     * @return: the shortest path for all nodes
     */
    public int shortestPathLength(int[][] graph) {
        // Write your code here.
        int len=graph.length;
        boolean[][] book=new boolean[len][1<<len];
        int k=(1<<len)-1;
        Queue<int[]> queue=new LinkedList<>();
        for (int i=0;i<len;i++)
        {
            queue.offer(new int[]{i,1<<i});
        }
        int step=0;
        while (!queue.isEmpty())
        {
            int size=queue.size();
            while (size-->0)
            {
                int[] node=queue.poll();
                if(k==node[1])return step;
                for (int next:graph[node[0]])
                {
                    int next_state=node[1]|(1<<next);
                    if(book[next][next_state])continue;
                    book[next][next_state]=true;
                    queue.offer(new int[]{next,next_state});
                }
            }
            step++;
        }
        return step;
    }
}
