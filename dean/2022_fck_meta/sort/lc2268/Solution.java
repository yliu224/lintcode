package sort.lc2268;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int minimumKeypresses(String s) {
        int[] chars = new int[26];
        for(char c:s.toCharArray()){
            chars[c-'a']++;
        }

        Key[] keys = new Key[26];
        for(int i=0;i<26;i++){
            keys[i] = new Key((char)('a'+i),chars[i]);
        }

        Arrays.sort(keys,(k1, k2)->k2.frequency-k1.frequency);

        Map<Character,Integer> keypad = new HashMap<>();
        for(int i=0;i<26;i++){
            if(keys[i].frequency==0){
                break;
            }
            keypad.put(keys[i].c,i/9+1);
        }

        int sum=0;
        for(char c:s.toCharArray()){
            sum+=keypad.get(c);
        }

        return sum;
    }
    class Key{
        char c;
        int frequency;
        Key(char c,int frequency){
            this.c = c;
            this.frequency = frequency;
        }
    }
}
