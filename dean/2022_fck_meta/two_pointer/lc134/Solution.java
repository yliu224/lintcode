package two_pointer.lc134;

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int i = 0;
        while (i < gas.length) {
            int stop = tryDrive(i, gas, cost);
            //注意这些判断条件
            if (stop >= gas.length && stop % gas.length == i) {
                return i;
            }
            if (stop == i) {
                i++;
            } else {
                i = stop;
            }

        }
        return -1;
    }

    //注意函数的定义，好的函数定义可以让算法变得易懂
    //而且好写
    private int tryDrive(int start, int[] gas, int[] cost) {
        int current = start;
        int len = gas.length;
        int tank = 0;
        do {
            tank += gas[current % len];
            tank -= cost[current % len];
            if (tank >= 0) {
                current++;
            } else {
                break;
            }
        } while (current % len != start);

        return current;
    }
}
