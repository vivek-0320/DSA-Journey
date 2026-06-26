import java.util.ArrayDeque;
import java.util.Deque;

public class MaxConsecutive1s {
    public int longestOnes(int[] nums, int k) {
        int length = 0;
        int l = 0;
        Deque<Integer> deq = new ArrayDeque<>();
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == 0) {
                if (deq.size() == k) {
                    if (deq.isEmpty())
                        l++;
                    else
                        l = deq.pollFirst() + 1;
                }
                deq.addLast(r);
            }
            length = Math.max(length, r - l + 1);
        }
        return length;
    }
}
