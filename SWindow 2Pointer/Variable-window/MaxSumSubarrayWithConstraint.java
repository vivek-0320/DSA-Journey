import java.util.ArrayDeque;
import java.util.Deque;

public class MaxSumSubarrayWithConstraint {
    public static long solve(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        long maxSum = Long.MIN_VALUE;
        Deque<Integer> deq = new ArrayDeque<>();
        deq.add(0);
        for (int i = 1; i <= n; i++) {
            while(!deq.isEmpty() && i - deq.peekFirst() > k) {
                deq.pollFirst();
            }
            maxSum = Math.max(maxSum,prefixSum[i]-prefixSum[deq.peekFirst()]);
            while(!deq.isEmpty() && prefixSum[deq.peekLast()] >= prefixSum[i] ) {
                deq.pollLast();
            }
            deq.add(i);
        }
        return maxSum;
    }
}
