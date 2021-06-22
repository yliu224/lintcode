package sort.leetc853_e;

import java.util.*;

public class Solution {
    class Car{
        int position;
        int speed;
        
        public Car(int position, int speed){
            this.position = position;
            this.speed = speed;
        }
    }
    public int carFleet(int target, int[] position, int[] speed) {
        if(position.length==0) return 0;
        List<Car> cars = new ArrayList<>();
        for(int i=0;i<position.length;i++){
            cars.add(new Car(position[i],speed[i]));
        }
        
        Collections.sort(cars,(a,b)->b.position-a.position);
                
        double[] times = new double[position.length];
        
        for(int i=0;i<cars.size();i++){
            Car car = cars.get(i);
            times[i] = (target-car.position)/(1.0*car.speed);
        }
        
        int count = 1;
        double longestTime = times[0];
        
        for(int i=1;i<times.length;i++){
            if(times[i]>longestTime){
                longestTime = times[i];
                count++;
            }
        }
        
        return count;
    }
}
