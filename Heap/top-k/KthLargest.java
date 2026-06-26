import java.util.PriorityQueue;

public class KthLargest {

    PriorityQueue<Integer> pq;
    int k;

    KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (pq.size() < k) {
            pq.offer(val);
        } else if (val > pq.peek()) {
            pq.offer(val);
            pq.poll();
        }
        return pq.peek();
    }
}