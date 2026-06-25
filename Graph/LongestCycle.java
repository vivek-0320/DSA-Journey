import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class LongestCycle {
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            inDegree[edges[i]]++;
        }
        Deque<Integer> deq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0)
                deq.offer(i);
        }
        boolean[] vis = new boolean[n];
        while (!deq.isEmpty()) {
            int u = deq.pollFirst();
            vis[u] = true;
            inDegree[edges[u]]--;
            if (inDegree[edges[u]] == 0)
                deq.offer(edges[u]);
        }

        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] > 0) {
                flag = false;
                break;
            }
        }
        if(flag)
            return -1;

        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                int curr = i;
                int len = 1;
                while (!vis[curr]) {
                    vis[curr] = true;
                    len += 1;
                    curr = edges[curr];
                }
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    public int longestCycle2(int[] edges) {
        int n = edges.length;
        
        // Instead of a boolean 'visited' array, we store the exact "time" we visited each node.
        // 0 means unvisited.
        int[] timeVisited = new int[n];
        int timer = 1; // Our global step counter
        
        // Initialize to -1 so we don't need a flag to check if no cycles were found
        int maxLen = -1; 

        for (int i = 0; i < n; i++) {
            // Only explore unvisited nodes
            if (timeVisited[i] == 0) {
                int startTime = timer; // Record the time we started THIS specific path
                int curr = i;

                // Walk forward until we hit a dead end (-1) or a node we've seen before
                while (curr != -1 && timeVisited[curr] == 0) {
                    timeVisited[curr] = timer++;
                    curr = edges[curr];
                }

                // If we hit a previously visited node, we need to check if it's a NEW cycle.
                // We know it's a cycle from OUR current walk if its visit time is >= startTime.
                // (If it's < startTime, we just bumped into an old path from a previous loop).
                if (curr != -1 && timeVisited[curr] >= startTime) {
                    int cycleLength = timer - timeVisited[curr];
                    maxLen = Math.max(maxLen, cycleLength);
                }
            }
        }
        
        return maxLen;
    }

}
