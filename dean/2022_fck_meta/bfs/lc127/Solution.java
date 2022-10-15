package bfs.lc127;

import java.util.*;

public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)){
            return 0;
        }
        Queue<String> q = new ArrayDeque<>();
        Queue<String> swap = new ArrayDeque<>();
        int words = 1;
        q.offer(beginWord);

        while(!q.isEmpty()){
            while(!q.isEmpty()){
                String w = q.poll();
                if(search(dict,w,endWord,swap)){
                    return ++words;
                };
            }
            words++;
            q.addAll(swap);
            swap.clear();
        }

        return 0;
    }

    private boolean search(Set<String> dict, String w, String endWord, Queue<String> swap){
        StringBuilder sb = new StringBuilder(w);
        for(int i=0;i<w.length();i++){
            char original = w.charAt(i);
            for(int c=0;c<26;c++){
                sb.replace(i,i,String.valueOf('a'+c));
                String tmp = sb.toString();
                if(tmp.equals(endWord)){
                    return true;
                }
                if(dict.contains(tmp)){
                    swap.offer(tmp);
                    dict.remove(tmp);
                }
            }
            sb.replace(i,i,String.valueOf(original));
        }
        return false;
    }
}
