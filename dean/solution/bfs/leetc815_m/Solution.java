package bfs.leetc815_m;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        Map<Integer, Set<Integer>> bus2Route = new HashMap<>();
        Map<Integer, Set<Integer>> route2Bus = new HashMap<>();
        Map<Integer, Set<Integer>> bus2Bus = new HashMap<>();

        for (int i = 0; i < routes.length; i++) {
            bus2Route.put(i, Arrays.stream(routes[i]).boxed().collect(Collectors.toSet()));
            for (int j = 0; j < routes[i].length; j++) {
                Set<Integer> buses = route2Bus.getOrDefault(routes[i][j], new HashSet<>());
                buses.add(i);
                route2Bus.put(routes[i][j], buses);
            }
        }

        for (int i = 0; i < routes.length; i++) {
            bus2Bus.put(i, new HashSet<>());
            for (int j = 0; j < routes[i].length; j++) {
                Set<Integer> buses = bus2Bus.get(i);
                buses.addAll(route2Bus.get(routes[i][j]));
                bus2Bus.put(i, buses);
            }
        }

        Queue<Integer> takingBuses = new LinkedList<>();
        Queue<Integer> pendingBuses = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int step = 0;

        takingBuses.addAll(route2Bus.get(source));
        visited.addAll(route2Bus.get(source));

        while (!takingBuses.isEmpty()) {
            while (!takingBuses.isEmpty()) {
                int bus = takingBuses.poll();
                if (bus2Route.get(bus).contains(target)) {
                    return step + 1;
                }
                for (int b : bus2Bus.get(bus)) {
                    if (!visited.contains(b)) {
                        pendingBuses.add(b);
                        visited.add(b);
                    }
                }
            }
            takingBuses.addAll(pendingBuses);
            pendingBuses.clear();
            step++;
        }

        return -1;
    }
}
