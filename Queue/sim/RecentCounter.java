import java.util.ArrayDeque;
import java.util.Queue;

class RecentCounter {
    Queue<Integer> queue;

    public RecentCounter() {
        this.queue = new ArrayDeque<>();
    }
    
    public int ping(int t) {
        queue.add(t);
        while (!queue.isEmpty() && (t - queue.peek() > 3000) ) {
            queue.remove();
        }
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */