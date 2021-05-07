package tier.lc270_m;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    class TrieNode {
        public Map<Integer,TrieNode> children;
        public int wordCount;

        public TrieNode(){
            children = new HashMap<>();
            wordCount = 0;
        }
    }
    /**
     * @param queries: the queries
     * @param dict: the words
     * @return: return the queries' answer
     */
    public int[] letterCombinationsII(String[] queries, String[] dict) {
        Map<Character,Integer> pad = new HashMap<>();
        pad.put('a',2);
        pad.put('b',2);
        pad.put('c',2);
        pad.put('d',3);
        pad.put('e',3);
        pad.put('f',3);
        pad.put('g',4);
        pad.put('h',4);
        pad.put('i',4);
        pad.put('j',5);
        pad.put('k',5);
        pad.put('l',5);
        pad.put('m',6);
        pad.put('n',6);
        pad.put('o',6);
        pad.put('p',7);
        pad.put('q',7);
        pad.put('r',7);
        pad.put('s',7);
        pad.put('t',8);
        pad.put('u',8);
        pad.put('v',8);
        pad.put('w',9);
        pad.put('x',9);
        pad.put('y',9);
        pad.put('z',9);

        //build Trie
        TrieNode root = new TrieNode();
        for(String s:dict){
            TrieNode node = root;
            for(char c:s.toCharArray()){
                int number = pad.get(c);
                if(!node.children.containsKey(number)){
                    node.children.put(number,new TrieNode());
                }
                node = node.children.get(number);
                node.wordCount++;
            }
        }

        int[] answer = new int[queries.length];
        for(int i=0;i<queries.length;i++){
            TrieNode node = root;
            int matches = 0;
            for(char c:queries[i].toCharArray()){
                if(node.children.containsKey(Integer.parseInt(String.valueOf(c)))){
                    node = node.children.get(Integer.parseInt(String.valueOf(c)));
                    matches = node.wordCount;
                } else{
                    matches = 0;
                    break;
                }
            }
            answer[i]=matches;
        }   

        return answer;
    }
}
