package tier.leetc624_m;

import java.util.*;
import java.util.stream.Collectors;

public class AutocompleteSystem {

    class TrieNode{
        Map<Character,TrieNode> children;
        boolean isWord;
        Word word;
        
        public TrieNode(){
            this.children = new HashMap<>();
            this.isWord = false;
            this.word = null;
        }
    }
    
    class Word{
        String word;
        int count;
        
        public Word(String word,int count){
            this.word = word;
            this.count = count;
        }
    }
    
    private StringBuilder search = new StringBuilder();
    private TrieNode root = new TrieNode();
    
    public AutocompleteSystem(String[] sentences, int[] times) { 
        for(int i=0;i<sentences.length;i++){
            TrieNode node = root;
            for(int j=0;j<sentences[i].length();j++){
                TrieNode child = node.children.getOrDefault(sentences[i].charAt(j),new TrieNode());
                node.children.put(sentences[i].charAt(j),child);
                node = child;
            }
            node.isWord = true;
            node.word = new Word(sentences[i],times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            addString(search.toString());
            search = new StringBuilder();
            return new ArrayList<>();
        }
        search.append(c);
        List<Word> results = new ArrayList<>();
        
        TrieNode node = root;
        String searchWord = search.toString();
        boolean isBreak = false;
        for(int i=0;i<searchWord.length();i++){
            TrieNode child = node.children.get(searchWord.charAt(i));
            if(child==null){
                isBreak = true;
                break;
            }
            node = child;
        }
        
        if(!isBreak){
            Queue<TrieNode> q = new LinkedList<>();
            q.add(node);

            while(!q.isEmpty()){
                TrieNode cur = q.poll();
                if(cur.isWord){
                    results.add(cur.word);
                }
                for(TrieNode e:cur.children.values()){
                    q.add(e);
                }
            }            
        }

        return results
            .stream()
            .sorted((a,b)->{
                if(a.count==b.count){
                    return a.word.compareTo(b.word);
                } else{
                    return b.count-a.count;
                }
            })
            .limit(3)
            .map(w->w.word)
            .collect(Collectors.toList());
    }

    private void addString(String str){
        TrieNode node = root;
        for(int i=0;i<str.length();i++){
            TrieNode child = node.children.getOrDefault(str.charAt(i),new TrieNode());
            node.children.put(str.charAt(i),child);
            node = child;
        }
        node.isWord = true;
        if(node.word!=null){
            node.word.count++;
        } else{
            node.word = new Word(str,1);
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
