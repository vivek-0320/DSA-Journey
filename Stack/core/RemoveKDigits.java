import java.util.*;

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (n - k == 0)
            return "0";
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : num.toCharArray()) {
            while (k != 0 && !stack.isEmpty() && stack.peek() > ch) {
                k--;
                stack.pop();
            }
            stack.push(ch);
        }
        StringBuilder newNum = new StringBuilder();
        while (!stack.isEmpty())
            newNum.append(stack.pop());
        newNum.reverse();
        int size = newNum.length();

        if (k != 0)
            newNum = new StringBuilder(newNum.substring(0, size - k));

        size = newNum.length();
        int countZero = 0;
        while (countZero < size && newNum.charAt(countZero) == '0')
            countZero++;
        String res = newNum.substring(countZero);
        if (res.length() == 0)
            return "0";
        return res;
    }
}
