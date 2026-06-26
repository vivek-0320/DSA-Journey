import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemp {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> st = new ArrayDeque<>();
        int[] res = new int[temperatures.length];
        for(int i = temperatures.length - 1 ; i >= 0; i--)
        {
            while(!st.isEmpty() && temperatures[st.peek()] <= temperatures[i]) {
                st.pop();
            }
            res[i] = st.isEmpty() ? 0 : st.peek() - i;
            st.push(i);
        }
        return res;
    }
}
