import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        boolean[] onStack = new boolean[26];
        int[] lastSeen = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (onStack[ch - 'a']) {
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > ch) {
                // If the top element never appears again, we cannot pop it
                if (i > lastSeen[stack.peek() - 'a']) {
                    break;
                }
                char top = stack.pop();
                onStack[top - 'a'] = false;
            }

            stack.push(ch);
            onStack[ch - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

}