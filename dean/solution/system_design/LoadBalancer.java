package system_design;

import java.util.*;

public class LoadBalancer {
    private List<Integer> lb;
    public LoadBalancer() {
        lb = new ArrayList<>();
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        lb.add(server_id);
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        for(int i=0;i<lb.size();i++){
            if(lb.get(i)==server_id){
                lb.remove(i);
            }
        }
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        int next = new Random().nextInt(lb.size()-1);
        return lb.get(next);
    }
}