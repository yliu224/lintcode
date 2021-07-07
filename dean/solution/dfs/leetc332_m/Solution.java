package dfs.leetc332_m;

import java.util.*;

public class Solution {
    List<String> result;
    boolean isFound;
    public List<String> findItinerary(List<List<String>> tickets) {
        isFound = false;
        Map<String,List<String>> routes = new HashMap<>();
        for(int i=0;i<tickets.size();i++){
            String from = tickets.get(i).get(0);
            List<String> route = routes.getOrDefault(from,new ArrayList<>());
            route.add(tickets.get(i).get(1));
            routes.put(from,route);
        }
        
        DFS(routes,new ArrayList<>(),"JFK");

        return result;
    }
    
    private void DFS(Map<String,List<String>> routes,List<String> path, String city){
        if(isFound){
            return;
        }
        if(routes.isEmpty()){
            path.add(city);
            result = new ArrayList<>(path);
            isFound = true;
            return;
        }
        List<String> to = routes.get(city);
        if(to==null) return;
        routes.remove(city);
        Collections.sort(to);
        for(int i=0;i<to.size();i++){
            path.add(city);
            String toCity = to.remove(i);
            if(to.isEmpty()){
                DFS(routes,path,toCity);
            } else{
                routes.put(city,to);
                DFS(routes,path,toCity);
            }
            path.remove(path.size()-1);
            to.add(i,toCity);
        }
        routes.put(city,to);
    }
}
