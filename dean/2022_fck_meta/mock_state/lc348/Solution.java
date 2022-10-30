package mock_state.lc348;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Map<String, Integer> p1 = new HashMap<>();
    private Map<String, Integer> p2 = new HashMap<>();
    private int n;

    public Solution(int n) {
        this.n = n;
    }

    public int move(int row, int col, int player) {
        List<String> ps = getPosition(row, col);
        Map<String, Integer> curPlayer = player == 1 ? p1 : p2;
        Map<String, Integer> anotherPlayer = player == 1 ? p2 : p1;
        for (String s : ps) {
            if (anotherPlayer.containsKey(s)) {
                anotherPlayer.put(s, -1);
                curPlayer.put(s, -1);
            } else {
                curPlayer.put(s, curPlayer.getOrDefault(s, 0) + 1);
            }
            if (curPlayer.get(s) == n) {
                return player;
            }
        }
        return 0;
    }

    private List<String> getPosition(int i, int j) {
        List<String> ps = new ArrayList<>();
        //注意这儿需要存的状态
        ps.add(j + "-" + "v");
        ps.add(i + "-" + "h");
        if (i == j) {
            ps.add("l");
        }
        if (i == n - 1 - j) {
            ps.add("r");
        }
        return ps;
    }
}
