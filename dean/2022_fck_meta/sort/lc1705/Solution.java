package sort.lc1705;

import java.util.PriorityQueue;

public class Solution {
    class Bucket {
        int apple;
        int rotDate;

        Bucket(int apple, int rotDate) {
            this.apple = apple;
            this.rotDate = rotDate;
        }
    }

    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<Bucket> buckets = new PriorityQueue<>((b1, b2) -> {
            if (b1.rotDate == b2.rotDate) {
                return b2.apple - b1.apple;
            }
            return b1.rotDate - b2.rotDate;
        });

        int sum = 0;

        for (int i = 0; i < apples.length; i++) {
            Bucket b = new Bucket(apples[i], i + days[i]);
            buckets.add(b);
            if (ateApple(buckets, i)) {
                sum++;
            }
        }
        int date = apples.length;
        while (!buckets.isEmpty()) {
            //注意细节
            if (ateApple(buckets, date++)) {
                sum++;
            }
        }
        return sum;
    }

    private boolean ateApple(PriorityQueue<Bucket> buckets, int date) {
        while (!buckets.isEmpty()) {
            Bucket b = buckets.poll();
            if (b.rotDate <= date) {
                continue;
            }
            b.apple--;
            if (b.apple != 0) {
                buckets.add(b);
            }
            return true;
        }
        return false;
    }
}
