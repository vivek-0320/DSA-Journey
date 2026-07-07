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

    // To handle negative numbers, standard sliding windows fail, so we use a Prefix
    // Sum array paired with a Monotonic Increasing Deque.
    // The deque stores indices of previous prefix sums. For each new prefix sum, we
    // pop from the front to calculate and
    // lock in the shortest valid subarrays, and we pop from the back to remove
    // larger prefix sums that
    // are objectively worse starting points (since they are both larger in value
    // and further away).

    public int shortestSubarrayOptimal(int[] nums, int k) {
        int n = nums.length;
        // Use long to prevent integer overflow when calculating sums
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Deque stores the INDICES of the prefix sum array
        Deque<Integer> deque = new ArrayDeque<>();
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            // Rule 1: Check if we found a valid subarray
            while (!deque.isEmpty() && prefix[i] - prefix[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }

            // Rule 2: Maintain monotonic increasing order
            while (!deque.isEmpty() && prefix[i] <= prefix[deque.peekLast()]) {
                deque.pollLast();
            }

            // Add current index to the deque
            deque.addLast(i);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}
