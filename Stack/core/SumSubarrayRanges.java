import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SumSubarrayRanges {
    public static long subArrayRanges(int[] nums) {
        long sum = 0;
        int n = nums.length;
        int[] NGL = new int[n]; // Next Greater Left
        int[] NGR = new int[n]; // Next Greater Right
        int[] NSL = new int[n]; // Next Smaller Left
        int[] NSR = new int[n]; // Next Smaller Right
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            NGL[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i > -1; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            NGR[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            NSL[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i > -1; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            NSR[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            int max = (NGR[i]-i)*(i-NGL[i]);
            int min = (NSR[i]-i)*(i-NSL[i]);
            sum = sum + (max-min)*nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++)
            ar[i] = sc.nextInt();
        System.out.println(subArrayRanges(ar));
        sc.close();
    }
}
