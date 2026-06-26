import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarraySumK {
    public int shortestSubarray(int[] nums, int k) {
        Deque<Integer> deq = new ArrayDeque<>();
        long[] prefix = new long[nums.length + 1];
        prefix[0] = 0;
        deq.add(0);
        int length = Integer.MAX_VALUE;
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
            while (!deq.isEmpty() && prefix[i] <= prefix[deq.peekLast()])
                deq.removeLast();
            while (!deq.isEmpty() && prefix[i] - prefix[deq.peekFirst()] >= k)
                length = Math.min(i - deq.pollFirst(), length);
            deq.add(i);
        }
        return length == Integer.MAX_VALUE ? -1 : length;
    }
}
