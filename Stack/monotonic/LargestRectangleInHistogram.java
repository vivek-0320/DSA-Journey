import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int area = 0;
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] NSL = new int[n];
        int[] NSR = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            NSL[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i > -1; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                stack.pop();
            }
            NSR[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            int newArea = heights[i] * (NSR[i] - NSL[i] - 1);
            area = Math.max(area, newArea);
        }
        return area;
    }

    public int largestRectangleArea2(int[] heights) {
        int area = 0;
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] LUI = new int[n];
        int[] RUI = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            LUI[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i > -1; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                stack.pop();
            }
            RUI[i] = stack.isEmpty() ? n - 1 : stack.peek() - 1;
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            int newArea = heights[i] * (RUI[i] - LUI[i] + 1);
            area = Math.max(area, newArea);
        }
        return area;
    }

    public int largestRectangleArea3(int[] heights) {
        class Pair {
            int index;
            int leftLimit;

            Pair(int i, int l) {
                index = i;
                leftLimit = l;
            }
        }
        int area = 0;
        int n = heights.length;
        Deque<Pair> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek().index] > heights[i]) {
                Pair top = stack.pop();
                int newArea = heights[top.index] * (i - top.leftLimit - 1);
                area = Math.max(area, newArea);
            }
            stack.push(new Pair(i, stack.isEmpty() ? -1 : stack.peek().index));
        }
        while (!stack.isEmpty()) {
            Pair top = stack.pop();
            int newArea = heights[top.index] * (n - top.leftLimit - 1);
            area = Math.max(area, newArea);
        }
        return area;
    }

    public int largestRectangleArea4(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i]; // Sentinel height 0 at the end
            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int right = i;
                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = right - left - 1;
                maxArea = Math.max(maxArea, h * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
