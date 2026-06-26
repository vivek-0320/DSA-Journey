import java.util.ArrayDeque;
import java.util.Deque;

class StockSpanner {
    private class Pair{
        public int price;
        public int days;

        Pair(int a,int b) {
            price = a;
            days = b;
        }
    }
    Deque<Pair> stack;

    public StockSpanner() {
        stack= new ArrayDeque<>();
    }
    
    public int next(int price) {
        int count = 1;
        while(!stack.isEmpty() && stack.peek().price <= price) {
            count += stack.pop().days;
        }
        stack.push(new Pair(price,count));
        return count;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */