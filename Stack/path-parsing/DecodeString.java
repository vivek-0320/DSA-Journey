import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {
    public String decodeString(String s) {
        Deque<StringBuilder> words = new ArrayDeque<>();
        Deque<Integer> repeat = new ArrayDeque<>();
        int num = 0;
        StringBuilder curr = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '[') {
                repeat.push(num);
                words.push(curr);
                num = 0;
                curr.setLength(0);
            } else if (ch == ']') {
                int rep = repeat.pop();
                StringBuilder temp = words.pop();
                for (int i = 0; i < rep; i++) {
                    temp.append(curr);
                }
                curr = temp;
            } else {
                curr.append(ch);
            }
        }
        return curr.toString();
    }
}