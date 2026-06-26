import java.util.*;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> queue = new ArrayDeque<>();
        int[] res = new int[n - k + 1];
        for (int i = n - 1; i > -1; i--) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i])
                queue.removeLast();
            queue.addLast(i);
            if (i <= n - k) {
                while (!queue.isEmpty() && queue.peekFirst() >= i + k)
                    queue.removeFirst();
                res[i] = nums[queue.peekFirst()];
            }
        }
        return res;

    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> queue = new ArrayDeque<>();
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i])
                queue.removeLast();

            queue.addLast(i);

            while (!queue.isEmpty() && queue.peekFirst() <= i - k)
                queue.removeFirst();
                
            if (i >= k - 1)
                res[i - k + 1] = nums[queue.peekFirst()];
        }
        return res;

    }
}
