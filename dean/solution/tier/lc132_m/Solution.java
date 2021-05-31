package tier.lc132_m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    class TrieNode {
        public Map<Character,TrieNode> children;
        public boolean isWord;
        public String word;

        public TrieNode(){
            children = new HashMap<>();
            isWord = false;
            word = "";
        }
    }

    private TrieNode root = new TrieNode();
    private int lenI;
    private int lenJ;

    public List<String> wordSearchII(char[][] board, List<String> words) {
        lenI = board.length;
        lenJ = board[0].length;
        //build Trie
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

        Set<String> matchedWords = new TreeSet<>();
        //BFS search
        Stack<Integer> visited = new Stack<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(root.children.containsKey(board[i][j])){
                    visited.add(getIndex(i,j));
                    matchedWords.addAll(searchWords(visited,getIndex(i,j),board,root.children.get(board[i][j])));
                    visited.pop();
                }
            }
        }

        return new ArrayList<>(matchedWords);
    }

    //注意这个地方要用DFS
    private List<String> searchWords(Stack<Integer> visited, int index,char[][] board,TrieNode node){
        List<String> matchedWords = new ArrayList<>();
        if(node.isWord){
            matchedWords.add(node.word);
        }

        for(int step=0;step<4;step++){
            try{
                int nextStep = getNextStep(index,step);
                if(canMove(visited,nextStep,board,node)){
                    visited.add(nextStep);
                    matchedWords.addAll(searchWords(visited, nextStep, board, node.children.get(board[getI(nextStep)][getJ(nextStep)])));
                    visited.pop();
                }
            } catch (RuntimeException e){
                continue;
            }
        }
        return matchedWords;
    }

    private boolean canMove(Stack<Integer> visited,int index,char[][] board,TrieNode node){
        if(visited.contains(index)){
            return false;
        }

        char c = board[getI(index)][getJ(index)];
        return node.children.containsKey(c);
    }

    private int getNextStep(int index,int step){
        int i = getI(index);
        int j = getJ(index);
        if(step == 0 && i>0){
            return getIndex(i-1,j);
        }

        if(step == 1 && i<lenI-1){
            return getIndex(i+1,j);
        }

        if(step == 2 && j>0){
            return getIndex(i,j-1);
        }

        if(step == 3 && j<lenJ-1){
            return getIndex(i,j+1);
        }

        throw new RuntimeException("Out of index error");
    }

    private int getIndex(int i,int j){
        return i*lenJ+j;
    }

    private int getI(int index){
        return index/lenJ;
    }
    
    private int getJ(int index){
        return index%lenJ;
    }
}