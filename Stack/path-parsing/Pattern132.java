import java.util.ArrayDeque;
import java.util.Deque;

public class Pattern132 {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3)
            return false;
        Deque<Integer> stack = new ArrayDeque<>();
        int k = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] < k)
                return true;
            while(!stack.isEmpty() && stack.peek() < nums[i]) {
                k = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
