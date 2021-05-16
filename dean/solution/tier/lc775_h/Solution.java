package tier.lc775_h;

import java.util.*;

public class Solution {
    /**
     * @param words: a list of unique words
     * @return: all pairs of distinct indices
     */

    class TrieNode {
        public Map<Character,TrieNode> children;
        public boolean isWord;
        public String word;
        public int index;

        public TrieNode(){
            children = new HashMap<>();
            isWord = false;
            word = "";
            index = -1;
        }
    }

    private TrieNode root = new TrieNode();
    public List<List<Integer>> palindromePairs(String[] words) {
        buildSufixTrie(words);
        Set<String> answer = new HashSet<>();
        for(int i=0;i<words.length;i++){
            answer.addAll(findPair(words[i],i));
        }

        List<List<Integer>> result = new ArrayList<>();
        for(String str:answer){
            String[] index = str.split(",");
            result.add(Arrays.asList(Integer.parseInt(index[0]),Integer.parseInt(index[1])));
        }

        return result;
    }

    private Set<String> findPair(String word,int index){
        List<TrieNode> candidates = search(word);

        Set<String> answer = new HashSet<>();
        for(TrieNode candidate:candidates){
            if(candidate.index!=index && isPalindrome(candidate.word,word)){
                answer.add(index+","+candidate.index);
            }
        }
        return answer;
    }

    private boolean isPalindrome(String w2,String w1){
        String combineStr = w1+w2;
        int start=0;
        int end = combineStr.length()-1;

        while(start<end){
            if(combineStr.charAt(start)!=combineStr.charAt(end)){
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    private List<TrieNode> search(String word){
        List<TrieNode> nodes = new ArrayList<>();
        TrieNode node = root;

        if(root.isWord) nodes.add(root);
        
        for(int i=0;i<word.length();i++){
            node = node.children.get(word.charAt(i));

            if(node==null) return nodes;
            if(node.isWord){
                nodes.add(node);
            }
        }

        Queue<TrieNode> q = new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()){
            TrieNode current = q.poll();
             for(TrieNode child:current.children.values()){
                 if(child.isWord){
                     nodes.add(child);
                 }
                 q.add(child);
             }
        }

        return nodes;
    }

    private void buildSufixTrie(String[] words){
        for(int i=0;i<words.length;i++){
            TrieNode node = root;
            for(int j=words[i].length()-1;j>=0;j--){
                if(!node.children.containsKey(words[i].charAt(j))){
                    node.children.put(words[i].charAt(j),new TrieNode());
                }
                node = node.children.get(words[i].charAt(j));
            }
            node.isWord = true;
            node.word = words[i];
            node.index = i;
        }
    }
}
