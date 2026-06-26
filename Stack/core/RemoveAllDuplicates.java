import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllDuplicates {
    public String removeDuplicates(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!st.isEmpty() && ch == st.peek())
                st.pop();
            else
                st.push(ch);
        }
        StringBuilder ans = new StringBuilder("");
        while (!st.isEmpty()) {
            ans.append(st.pop());
        }
        return ans.reverse().toString();
    }
}
