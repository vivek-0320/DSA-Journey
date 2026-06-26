import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class EvaluateRPN {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (token.matches("^[+-]?\\d+$")) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                int res = 0;
                switch (token) {
                    case "+":
                        res = a + b;
                        break;
                    case "-":
                        res = a - b;
                        break;
                    case "*":
                        res = a * b;
                        break;
                    case "/":
                        res = a / b;
                        break;
                    default:
                        break;
                }
                stack.push(res);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num = sc.nextLine();
        boolean isNumeric = num.matches("\\d+");
        System.out.println(isNumeric);
        sc.close();
    }
}
