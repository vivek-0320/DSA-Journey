import java.util.ArrayDeque;
import java.util.Deque;

public class CircularGame {
    int findTheWinner(int n, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i=1;i<=n;i++) 
            queue.add(i);
        int count=0;
        while (queue.size() > 1) {
            count++;
            if(count == k)
                queue.remove();
            else
                queue.add(queue.remove());
        }
        return queue.remove();
    }

    public static void main(String[] args) {
        
    }
}
