import java.util.*;

public class ReorganiseString {
    public String reorganizeString(String s) {
        int[] map = new int[26];
        for (char c : s.toCharArray())
            map[c - 'a']++;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]); // { char , freq }
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0)
                pq.offer(new int[] { i, map[i] });
        }
        Queue<int[]> cooldown = new LinkedList<>(); // { char , freq , next }
        StringBuilder newString = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (!cooldown.isEmpty() && cooldown.peek()[2] <= i) {
                int[] top = cooldown.poll();
                pq.offer(new int[] { top[0], top[1] });
            }
            if (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int ch = curr[0];
                int freq = curr[1];
                newString.append((char) (curr[0] + 'a'));
                if (freq - 1 > 0)
                    cooldown.add(new int[] { ch, freq - 1, i + 2 });
            } else {
                break;
            }
            i++;
        }
        return i == s.length() ? newString.toString() : "";
    }
}
