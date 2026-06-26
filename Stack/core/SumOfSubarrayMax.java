import java.util.*;

public class SumOfSubarrayMax {
    public static long subarrayMaxSum(int[] ar) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = ar.length;
        int[] NGL = new int[n];
        int[] NGR = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && ar[stack.peek()] <= ar[i]) {
                stack.pop();
            }
            NGL[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i > -1; i--) {
            while (!stack.isEmpty() && ar[stack.peek()] < ar[i]) {
                stack.pop();
            }
            NGR[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long left = (i - NGL[i]);
            long right = (NGR[i] - i);
            sum = sum + ((1L * ar[i] * left) * right);
        }
        return sum;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] list = new int[n];
        for(int i=0;i<n;i++)
            list[i] = sc.nextInt();
        System.out.println("Max Sum: "+subarrayMaxSum(list));
        sc.close();

    }
}