import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;

public class OpenLocks {

    class Node {
        String s;
        int d;

        Node(String s, int d) {
            this.s = s;
            this.d = d;
        }
    }

    public ArrayList<String> generateNeighbours(String s) {
        ArrayList<String> neighbors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StringBuilder front = new StringBuilder(s);
            StringBuilder back = new StringBuilder(s);
            char ch = s.charAt(i);
            int spinUp = (ch - '0' + 1) % 10;
            int spindDown = (ch - '0' + 9) % 10;
            front.setCharAt(i, (char) (spinUp + '0'));
            back.setCharAt(i, (char) (spindDown + '0'));
            neighbors.add(front.toString());
            neighbors.add(back.toString());
        }
        return neighbors;
    }

    public int openLock(String[] deadends, String target) {
        HashSet<String> visited = new HashSet<>();
        for (String str : deadends)
            visited.add(str);
        if(visited.contains("0000"))
            return -1;
        Deque<Node> deq = new ArrayDeque<>();
        deq.offer(new Node("0000", 0));
        visited.add("0000");
        while (!deq.isEmpty()) {
            Node curr = deq.poll();
            if (curr.s.equals(target))
                return curr.d;

            for (String neighbor : generateNeighbours(curr.s)) {
                //System.out.println(curr.d + " -> " + neighbor);
                if (!visited.contains(neighbor)) {
                    deq.offer(new Node(neighbor, curr.d + 1));
                    visited.add(neighbor);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        OpenLocks ob = new OpenLocks();
        String[] deadends = { "0201", "0101", "0102", "1212", "2002" };
        String target = "0202";
        System.out.println(ob.openLock(deadends, target));
    }
}