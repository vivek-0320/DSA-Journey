import java.util.*;


// The maximum water trapped at any spot is bounded by the shorter of the tallest walls to its 
// left and right minus the current ground height: min(left_max, right_max) - height[i].
//
// DP Approach: Precomputes every spot's left and right limits by running two initial passes (left-to-right and right-to-left)
// storing values in arrays.
//
// Two-Pointer Approach: Using two pointers from the outside edges inward 
// because the absolute bottleneck is always dictated by the smaller of the two outer maximums, 
// you can confidently calculate water and move the smaller pointer forward.

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

    public int trapDP(int[] height) {
        if (height == null || height.length == 0)
            return 0;

        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Fill leftMax array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        // Fill rightMax array
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        // Calculate accumulated water
        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return water;
    }

    public static int trap3(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = height[left], rightMax = height[right], water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                water += (leftMax - height[left]);
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                water += (rightMax - height[right]);
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] ar = { 3, 0, 2, 0, 4 };
        System.out.println(trap3(ar));
    }
}
