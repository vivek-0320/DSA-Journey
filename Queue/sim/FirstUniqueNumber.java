import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class FirstUniqueNumber {
    Deque<Integer> queue;
    HashMap<Integer, Integer> map;

    FirstUniqueNumber() {
        queue = new ArrayDeque<>();
        map = new HashMap<>();
    }

    public void add(int val) {
        int freq = map.getOrDefault(val, 0) + 1;
        map.put(val, freq);
        if (freq == 1)
            queue.add(val);
    }

    public int showFirstUnique() {
        while (!queue.isEmpty() && map.get(queue.peek()) > 1)
            queue.pop();
        if (queue.isEmpty())
            return -1;
        else
            return queue.peek();
    }

    public static void main(String[] args) {
        FirstUniqueNumber ob = new FirstUniqueNumber();
        ob.add(2);
        System.out.println(ob.showFirstUnique());
        ob.add(3);
        System.out.println(ob.showFirstUnique());
        ob.add(5);
        System.out.println(ob.showFirstUnique());
        ob.add(5);
        System.out.println(ob.showFirstUnique());
        ob.add(2);
        System.out.println(ob.showFirstUnique());
        ob.add(3);
        System.out.println(ob.showFirstUnique());
    }
}
