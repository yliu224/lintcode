package bfs.lc2115;

import java.util.*;

public class Solution {
    class Food {
        String name;
        boolean isSupply;
        Set<String> outCome;
        Set<String> inCome;

        Food(String name) {
            this.isSupply = false;
            this.name = name;
            this.outCome = new HashSet<>();
            this.inCome = new HashSet<>();
        }
    }

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Food> foods = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            Food f = foods.computeIfAbsent(recipes[i], Food::new);
            for (int j = 0; j < ingredients.get(i).size(); j++) {
                Food ing = foods.computeIfAbsent(ingredients.get(i).get(j), Food::new);
                f.inCome.add(ing.name);
                ing.outCome.add(f.name);
            }
        }

        Queue<Food> q = new LinkedList<>();
        for (String s : supplies) {
            Food f = foods.computeIfAbsent(s, Food::new);
            f.isSupply = true;
            q.add(f);
        }

        List<String> result = new ArrayList<>();
        while (!q.isEmpty()) {
            Food cur = q.poll();
            for (String s : cur.outCome) {
                Food next = foods.get(s);
                next.inCome.remove(cur.name);
                if (next.inCome.size() == 0) {
                    q.add(next);
                    result.add(next.name);
                }
            }
        }
        return result;
    }
}
