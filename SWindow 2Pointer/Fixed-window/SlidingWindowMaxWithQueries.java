import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SlidingWindowMaxWithQueries {
    public static List<Integer> solve(List<Integer> arr, List<Integer> queries) {
        // Write your code here
        Deque<Integer> deq = new ArrayDeque<>();
        int n = arr.size();
        List<Integer> res = new ArrayList<>();
        for (Integer k : queries) {
            deq.clear();
            int minQuery = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                while (!deq.isEmpty() && deq.peekFirst() <= i - k) // Remiving expired indices
                    deq.pollFirst();

                while (!deq.isEmpty() && arr.get(deq.peekLast()) < arr.get(i))
                    deq.pollLast();

                deq.add(i);

                if (i + 1 - k >= 0) {
                    minQuery = Math.min(minQuery, deq.peekFirst());
                }
            }
            res.add(minQuery);
        }
        return res;
    }
}
