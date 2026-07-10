import java.util.ArrayDeque;
import java.util.Deque;

public class MinMaxRiddle {
    static long[] riddle(long[] arr) {
        // complete this function
        Deque<Integer> stack = new ArrayDeque<>();
        int n = arr.length;
        long[] PSE = new long[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            PSE[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        long[] NSE = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i])
                stack.pop();
            NSE[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long[] windowLength = new long[n];
        for (int i = 0; i < n; i++)
            windowLength[i] = NSE[i] - PSE[i] - 1;

       long[] ans = new long[n + 1];
        for (int i = 0; i < n; i++) {
            int len = (int) windowLength[i];
            ans[len] = Math.max(ans[len], arr[i]);
        }
        
        for (int i = n - 1; i >= 1; i--) {
            ans[i] = Math.max(ans[i], ans[i + 1]);
        }
        
        long[] result = new long[n];
        for (int i = 1; i <= n; i++) {
            result[i - 1] = ans[i];
        }
        
        return result;
    }
}
