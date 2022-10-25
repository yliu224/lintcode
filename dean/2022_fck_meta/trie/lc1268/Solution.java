package trie.lc1268;

import java.util.*;

public class Solution {
    private TrieNode root = new TrieNode();
    private List<List<String>> result = new ArrayList<>();
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        //build tree
        for(String s:products){
            TrieNode n=root;
            for(char c:s.toCharArray()){
                n = n.children.computeIfAbsent(c,x->new TrieNode());
            }
            n.isWord = true;
            n.word = s;
        }

        //search
        DFS(root,searchWord);
        result.remove(0);
        return result;
    }

    //思考清楚每一步是怎么走的
    //1.先找到拿个char，往下走
    //2.再把char的兄弟都给 for一边
    private List<String> DFS(TrieNode n,String word){
        List<String> ans = new ArrayList<>();
        if(n==null){
            for(int i=0;i<word.length()+1;i++){
                result.add(ans);
            }
            return ans;
        }
        if(word.isEmpty()){
            ans.addAll(findAllWords(n));
            result.add(ans);
            return ans;
        }
        if(n.isWord){
            ans.add(n.word);
        }
        char c = word.charAt(0);
        ans.addAll(DFS(n.children.get(c),word.substring(1)));
        for (Map.Entry<Character, TrieNode> m : n.children.entrySet()) {
            if (m.getKey() == c) continue;
            ans.addAll(findAllWords(m.getValue()));
        }
        Collections.sort(ans);
        result.add(0, ans.subList(0,Math.min(3,ans.size())));
        return ans;

    }

    private List<String> findAllWords(TrieNode n){
        List<String> ans = new ArrayList<>();
        if(n.isWord){
            ans.add(n.word);
        }
        for(TrieNode node:n.children.values()){
            ans.addAll(findAllWords(node));
        }
        Collections.sort(ans);
        return ans.subList(0,Math.min(3,ans.size()));
    }

    class TrieNode{
        Map<Character,TrieNode> children = new HashMap<>();
        boolean isWord = false;
        String word = "";
    }
}
