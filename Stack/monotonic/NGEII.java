import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NGEII {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = (n * 2) - 1; i >= 0; i--) {
            int num = nums[i % n];
            while (!stack.isEmpty() && stack.peek() <= num) {
                stack.pop();
            }
            if(i < n && !stack.isEmpty()) {
                ans[i]=stack.peek();
            }
            stack.push(num);
        }
        return ans;
    }
}
