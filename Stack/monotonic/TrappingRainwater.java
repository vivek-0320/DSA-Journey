import java.util.*;

public class TrappingRainwater {
    public static int trap(int[] height) {
        int water = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int n = height.length;
        int[] NGL = new int[n];
        int[] NGR = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                stack.pop();
            }
            NGL[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i > -1; i--) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                stack.pop();
            }
            NGR[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            if (NGL[i] != -1 && NGR[i] != -1 && height[NGL[i]] > height[i] && height[NGR[i]] > height[i]) {
                int width = NGR[i] - NGL[i] - 1;
                int len = Math.min(height[NGL[i]], height[NGR[i]]) - height[i];
                water += (width * len);
            }
        }
        return water;
    }

    public static int trap2(int[] height) {
        int water = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int base = height[stack.pop()];
                if (stack.isEmpty())
                    break;
                int left = stack.peek();
                int width = i - left - 1;
                water += (width * (Math.min(height[i], height[left]) - base));
            }
            stack.push(i);
        }
        return water;
    }

    public static int trap3(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] < leftMax) {
                    water += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                left++;
            } else {
                if (height[right] < rightMax) {
                    water += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] ar = { 3, 0, 2, 0, 4 };
        System.out.println(trap3(ar));
    }
}
