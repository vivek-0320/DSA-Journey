import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveDuplicatesII {
    private class Pair {
        public char ch;
        public int count;

        Pair(char ch,int count) {
            this.ch = ch;
            this.count = count;
        }
    }
    public String removeDuplicates(String s, int k) {
        Deque<Pair> stack = new ArrayDeque<>();
        for(char ch : s.toCharArray())
        {
            if(!stack.isEmpty() && stack.peek().ch == ch) {
                stack.peek().count++;
                if(stack.peek().count == k)
                    stack.pop();
            } else {
                stack.push(new Pair(ch,1));
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair temp = stack.pop();
            for(int i=0;i<temp.count;i++)
                ans.append(temp.ch);

        }
        return ans.reverse().toString();
    }
}
