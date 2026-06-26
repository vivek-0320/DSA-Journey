import java.util.ArrayDeque;
import java.util.Deque;

public class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(0);
            } else {
                int inner = stack.pop(); // score inside this pair
                int outer = stack.pop(); // score outside
                int added = inner == 0 ? 1 : 2 * inner;
                stack.push(outer + added);
            }
        }
        return stack.pop();
    }
}
