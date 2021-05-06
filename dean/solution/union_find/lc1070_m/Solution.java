package union_find.lc1070_m;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    /**
     * @param accounts: List[List[str]]
     * @return: return a List[List[str]]
     */
    private Map<String,String> email2Account = new HashMap<>();
    private Map<String,String> set = new HashMap<>();
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        buildEmail2AccountMap(accounts);
        mergeAccounts(accounts);
        return printNewAccountInfo(accounts);
    }

    private List<List<String>> printNewAccountInfo(List<List<String>> accounts){
        Map<String, Set<String>> newInfo = new HashMap<>();
        
        for(int i=0;i<accounts.size();i++){
            List<String> account = accounts.get(i);
            for(int j=1;j<account.size();j++){
                String root = compressed_find(account.get(j));
                if(newInfo.containsKey(root)){
                    newInfo.get(root).add(account.get(j));
                } else{
                    newInfo.put(root,new TreeSet<>(Arrays.asList(account.get(j))));
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<String, Set<String>> e: newInfo.entrySet()){
            String accName = email2Account.get(e.getKey());
            List<String> newAccInfo = new ArrayList<>(Arrays.asList(accName));
            newAccInfo.addAll(e.getValue());
            result.add(newAccInfo);
        }

        return result;
    }

    private void buildEmail2AccountMap(List<List<String>> accounts){
        for(int i=0;i<accounts.size();i++){
            List<String> account = accounts.get(i);
            for(int j=1;j<account.size();j++){
                email2Account.put(account.get(j),account.get(0));
            }
        }
    }

    private void mergeAccounts(List<List<String>> accounts){
        for(int i=0;i<accounts.size();i++){
            List<String> account = accounts.get(i);
            for(int j=2;j<account.size();j++){
                merge(account.get(j-1),account.get(j));
            }
        }
    }

    private void merge(String a,String b){
        String rootA = compressed_find(a);
        String rootB = compressed_find(b);

        if(!rootA.equals(rootB)){
            set.put(rootA,rootB);
        }
    }

    private String compressed_find(String email){
        String root = email;
        while(set.get(root)!=null){
            root = set.get(root);
        }

        while(set.get(email)!=null){
            String tmp = set.get(email);
            set.put(email,tmp);
            email=tmp;
        }

        return root;
    }
}