package dfs.lc40;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    private Set<String> visited = new HashSet<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> cans = Arrays.stream(candidates).boxed().sorted().collect(Collectors.toList());
        for (int i = 0; i < candidates.length; i++) {
            int num = cans.remove(0);
            StringBuilder sb = new StringBuilder();
            DFS(target - num, cans, new LinkedList<>(List.of(num)), sb.append(num));
            //cans.add(i, num);
        }
        return result;
    }

    private void DFS(int target, List<Integer> cans, LinkedList<Integer> path,StringBuilder sb) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            if(!visited.contains(sb.toString())){
                result.add(new ArrayList<>(path));
                visited.add(sb.toString());
            }

            return;
        }
        List<Integer> tmp = new ArrayList<>();
        int size = cans.size();
        for (int i = 0; i < size; i++) {
            int num = cans.remove(0);
            tmp.add(num);
            path.addLast(num);
            DFS(target - num, cans, path,sb.append(",").append(num));
            sb.delete(sb.lastIndexOf(","),sb.length());
            path.removeLast();
        }
        cans.addAll(tmp);
    }
}
