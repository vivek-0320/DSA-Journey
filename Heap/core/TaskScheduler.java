import java.util.*;

public class TaskScheduler {
    public static int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]); // { freq , characterIdx }
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                pq.offer(new int[] { map[i], i });
            }
        }
        Queue<int[]> cooldown = new LinkedList<>(); // { remaningFreq , characterIdx , availableAt }
        int time = 0;
        while (!pq.isEmpty() || !cooldown.isEmpty()) {
            time++;
            if (!cooldown.isEmpty() && cooldown.peek()[2] == time) {
                pq.offer(new int[] { cooldown.peek()[0], cooldown.peek()[1] });
                cooldown.poll();
            }
            if (!pq.isEmpty()) {
                int[] curr = pq.poll();
                curr[0]--;
                if (curr[0] > 0) {
                    cooldown.offer(new int[] { curr[0], curr[1], time + n + 1 });
                }
            }
        }
        return time;
    }

    public int leastInterval2(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks)
            freq[c - 'A']++;

        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> { // Each element: [nextAvailableTime, remainingFreq, taskChar]
            if (a[0] != b[0])
                return a[0] - b[0]; // earliest available first
            return b[1] - a[1]; // then highest frequency
        });


        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0)
                pq.offer(new int[] { 0, freq[i], i });  // Initialize: all available at time 0
        }
        int time = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int availableAt = curr[0], remaining = curr[1], ch = curr[2];
            if (time < availableAt) {
                time = availableAt;
            }
            time++;
            remaining--;
            if (remaining > 0) {
                pq.offer(new int[] { time + n, remaining, ch });
            }
        }
        return time;
    }

    public static void main(String[] args) {
        char[] tasks = { 'A', 'A', 'B', 'B', 'C', 'D' };
        int n = 1;
        int time = leastInterval(tasks, n);
        System.out.println(time);
    }
}
