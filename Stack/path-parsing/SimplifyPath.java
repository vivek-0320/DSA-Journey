import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SimplifyPath {
    public static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder dir = new StringBuilder();
        for (int i = 0; i <= path.length(); i++) {
            char ch = (i < path.length()) ? path.charAt(i) : '/'; // Final flush with extra slash
            if (ch == '/') {
                String part = dir.toString();
                if (part.equals("..")) {
                    if (!stack.isEmpty())
                        stack.pop();
                } else if (!part.equals("") && !part.equals(".")) {
                    stack.push(part);
                }
                dir.setLength(0); // Clear for next segment
            } else {
                dir.append(ch);
            }
        }
        if (stack.isEmpty())
            return "/";
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, "/" + stack.pop());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        String ans = simplifyPath(path);
        System.out.println("Ans: " + ans);
        sc.close();
    }
}
