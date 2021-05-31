package tier.lc634_m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    /**
     * @param words: a set of words without duplicates
     * @return: all word squares
     */

    class TrieNode{
        public Map<Character,TrieNode> children;
        public boolean isWord;
        public String word;

        public TrieNode(){
            children = new HashMap<>();
            word = "";
            isWord = false;
        }
    }
    private TrieNode root = new TrieNode();
    private List<List<String>> results = new ArrayList<>();

    public List<List<String>> wordSquares(String[] words) {
        buildTrie(words);
        DFS("",new Stack<>());
        return results;
    }

    //可以重复使用 
    private void DFS(String prefix ,Stack<String> square){
        if(square.size()!=0 && square.get(0).length()==square.size()){
            results.add(new ArrayList<String>(square));//注意Stack的操作
            return;
        }
        List<String> validWords = findAllWords(prefix);
        for(String w:validWords){
            square.push(w);
            String newPrefix = buildPrefix(square);
            DFS(newPrefix,square);
            square.pop();
        }
    }

    private String buildPrefix(Stack<String> square){
        int index = square.size();

        //注意考虑一下edge case
        if(index==square.peek().length()){
            return square.peek();
        }

        StringBuilder sb =new StringBuilder();
        for(String s:square){
            sb.append(s.charAt(index));
        }
        return sb.toString();
    }

    private void buildTrie(String[] words){
        for(String word:words){
            TrieNode node = root;
            for(char c:word.toCharArray()){
                if(!node.children.containsKey(c)){
                    node.children.put(c,new TrieNode());
                }
                node = node.children.get(c);
            }
            node.isWord = true;
            node.word = word;
        }
    }

    private List<String> findAllWords(String prefix){
        TrieNode node = root;
        List<String> words = new ArrayList<>();

        for(char c:prefix.toCharArray()){
            if(node.children.containsKey(c)){
                node = node.children.get(c);
            } else{
                return new ArrayList<>();
            }
        }

        Queue<TrieNode> q = new LinkedList<>();
        q.offer(node);

        while(!q.isEmpty()){
            TrieNode tn = q.poll();
            if(tn.isWord){
                words.add(tn.word);
            }
            q.addAll(tn.children.values());
        }

        return words;
    }
}
