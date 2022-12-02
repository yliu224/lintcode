package dfs.lc394;

public class Solution {
    public String decodeString(String s) {
        return DFS(new StringBuilder(s));
    }

    String DFS(StringBuilder sb) {
        StringBuilder result = new StringBuilder();
        int rep = 0;
        while (sb.length() > 0) {
            String str = getStringOrInteger(sb);
            sb.delete(0, str.length());
            if (str.equals("]")) {
                return result.toString();
            }
            if (isNumber(str)) {
                rep = Integer.parseInt(str);
                String dfsStr = DFS(sb.deleteCharAt(0));
                for (int i = 0; i < rep; i++) {
                    result.append(dfsStr);
                }
            } else {
                result.append(str);
            }
        }
        return result.toString();
    }

    boolean isNumber(String str) {
        return str.charAt(0) >= '0' && str.charAt(0) <= '9';
    }

    String getStringOrInteger(StringBuilder sb) {
        if (sb.charAt(0) == ']') {
            return String.valueOf("]");
        }
        boolean isNum = (sb.charAt(0) >= '0' && sb.charAt(0) <= '9');
        int index = 0;
        StringBuilder ret = new StringBuilder();
        while (index < sb.length()) {
            ret.append(sb.charAt(index));
            index++;
            if (index <= sb.length() - 1) {
                if (isNum && sb.charAt(index) == '[') {
                    return ret.toString();
                }
                if (!isNum && ((sb.charAt(index) >= '0' && sb.charAt(index) <= '9') || sb.charAt(index) == ']')) {
                    return ret.toString();
                }
            }
        }
        return ret.toString();
    }
}
