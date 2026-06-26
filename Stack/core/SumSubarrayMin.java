import java.util.*;

public class SumSubarrayMin {
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] NSL = new int[n]; // Nearest Smaller Left
        int[] NSR = new int[n]; // Nearest Smaller Right
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            NSL[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i > -1; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            NSR[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        int MOD = 1_000_000_007;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int right = NSR[i] - i;
            int left = i - NSL[i];
            long partial = ((1L * arr[i] * right) % MOD);
            sum = (sum + (partial * left) % MOD) % MOD;

        }
        return (int) sum;
    }

    public static int sumSubarrayMins2(int[] ar) {
        long sum = 0;
        int MOD = 1_000_000_007;
        for (int size = 1; size <= ar.length; size++) {
            for (int x = 0; x <= ar.length - size; x++) {
                int min = ar[x];
                for (int k = x; k < size+x; k++) {
                    min = Math.min(min, ar[k]);
                }
                sum = (sum + min) % MOD;
            }
        }
        return (int) sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++)
            ar[i] = sc.nextInt();
        System.out.println(sumSubarrayMins2(ar));
        sc.close();
    }
}
