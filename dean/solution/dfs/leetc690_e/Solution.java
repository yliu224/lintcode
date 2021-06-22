package dfs.leetc690_e;

import java.util.*;

import datastructure.Employee;

public class Solution {
    private Map<Integer, Employee> orgMap;

    public int getImportance(List<Employee> employees, int id) {
        orgMap = new HashMap<>();
        for (Employee e : employees) {
            orgMap.put(e.id, e);
        }
        return orgMap.containsKey(id) ? DFS(orgMap.get(id)) : -1;
    }

    private int DFS(Employee em) {
        if (em.subordinates == null || em.subordinates.size() == 0) {
            return em.importance;
        }
        int sum = em.importance;

        for (int i = 0; i < em.subordinates.size(); i++) {
            sum += DFS(orgMap.get(em.subordinates.get(i)));
        }

        return sum;
    }
}
