import java.util.*;

public class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        int[] map = new int[26];
        Arrays.fill(map, -1);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (map[idx] == -1) {
                map[idx] = i;
                pq.offer(i);
            } else {
                pq.remove(map[idx]);
            }
        }
        return pq.size() == 0 ? -1 : pq.peek();
    }
}
