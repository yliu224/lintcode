package bfs.lc207;

import java.util.*;

public class Solution {
    class Node {
        Set<Integer> inCome;
        Set<Integer> outCome;
        int val;

        Node(int val) {
            this.val = val;
            this.inCome = new HashSet<>();
            this.outCome = new HashSet<>();
        }

        @Override
        public boolean equals(Object o) {
            return ((Node) o).val == this.val;
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Set<Integer> rootCourse = new HashSet<>();
        Map<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            rootCourse.add(i);
        }
        for (int[] p : prerequisites) {
            rootCourse.remove(p[1]);
            graph.computeIfAbsent(p[0], Node::new).outCome.add(p[1]);
            graph.computeIfAbsent(p[1], Node::new).inCome.add(p[0]);
        }
        if (rootCourse.isEmpty()) {
            return false;
        }

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.addAll(rootCourse);
        visited.addAll(rootCourse);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.getOrDefault(cur, new Node(cur)).outCome) {
                graph.get(next).inCome.remove(cur);
                if (graph.get(next).inCome.isEmpty()) {
                    visited.add(next);
                    q.offer(next);
                }
            }
        }
        return visited.size() == numCourses;
    }

}
