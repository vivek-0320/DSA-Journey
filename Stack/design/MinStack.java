import java.util.*;

public class MinStack {
    private static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Deque<Pair> st;

    public MinStack() {
        st = new ArrayDeque<>();
    }

    public void push(int val) {
        if (st.isEmpty()) {
            st.push(new Pair(val, val));
            return;
        }
        Pair top = st.peek();
        st.push(new Pair(val, Math.min(val, top.y)));
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        Pair top = st.peek();
        return top.x;
    }

    public int getMin() {
        Pair top = st.peek();
        return top.y;
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());        
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */