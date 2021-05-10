package tier.lc1848_h;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;


//TLE 需要优化
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
    private int max = 0;

    public int wordSearchIII(char[][] board, List<String> words) {
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

        Stack<String> matchedWords = new Stack<>();
        
        //BFS search
        Stack<Integer> visited = new Stack<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                matchedWords.clear();
                if(root.children.containsKey(board[i][j])){
                    visited.add(getIndex(i,j));
                    searchWords(visited,getIndex(i,j),board,root.children.get(board[i][j]),matchedWords);
                    visited.pop();
                }
            }
        }

        return max;
    }

    //注意这个地方要用DFS
    private void searchWords(Stack<Integer> visited, int index,char[][] board,TrieNode node,Stack<String> matchedWords){
        if(visited.size()==lenI*lenJ){
            HashSet<String> sets = new HashSet<>(matchedWords);//重复的不要计算
            max = Math.max(max,sets.size());
        }
        if(node.isWord){
            matchedWords.add(node.word);
            HashSet<String> sets = new HashSet<>(matchedWords);
            max = Math.max(max,sets.size());
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[i].length;j++){
                    if(canMove(visited, getIndex(i, j), board, root)){
                        visited.add(getIndex(i,j));
                        searchWords(visited,getIndex(i,j),board,root.children.get(board[i][j]),matchedWords);
                        visited.pop();
                    }
                }
            }
            matchedWords.pop();
        }

        for(int step=0;step<4;step++){
            try{
                int nextStep = getNextStep(index,step);
                if(canMove(visited,nextStep,board,node)){
                    visited.add(nextStep);
                    searchWords(visited, nextStep, board, node.children.get(board[getI(nextStep)][getJ(nextStep)]),matchedWords);
                    visited.pop();
                }
            } catch (RuntimeException e){
                continue;
            }
        }
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