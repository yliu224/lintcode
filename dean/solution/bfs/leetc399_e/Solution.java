package bfs.leetc399_e;

import java.util.*;

public class Solution {
    class Node {
        Map<String, Double> children;
        String val;

        public Node(String val) {
            this.val = val;
            this.children = new HashMap<>();
        }
    }

    private Map<String, Node> graph;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        graph = new HashMap<>();
        buildGraph(equations, values);
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i] = search(queries.get(i));
        }

        return result;
    }

    private void buildGraph(List<List<String>> equations, double[] values) {
        for (int i = 0; i < equations.size(); i++) {
            String strA = equations.get(i).get(0);
            String strB = equations.get(i).get(1);
            Node a = graph.getOrDefault(strA, new Node(strA));
            Node b = graph.getOrDefault(strB, new Node(strB));

            a.children.put(strB, values[i]);
            b.children.put(strA, 1.0 / values[i]);

            graph.put(strA, a);
            graph.put(strB, b);
        }
    }

    private double search(List<String> query) {
        String start = query.get(0);
        String end = query.get(1);

        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return -1.0;
        }

        Queue<String> q = new LinkedList<>();
        Queue<Double> products = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.add(start);
        products.add(1.0);
        visited.add(start);

        while (!q.isEmpty()) {
            String cur = q.poll();
            double product = products.poll();
            if (cur.equals(end)) {
                return product;
            }
            Node node = graph.get(cur);
            for (String child : node.children.keySet()) {
                if (!visited.contains(child)) {
                    visited.add(child);
                    q.add(child);
                    products.add(product * node.children.get(child));
                }
            }
        }

        return -1.0;
    }
}