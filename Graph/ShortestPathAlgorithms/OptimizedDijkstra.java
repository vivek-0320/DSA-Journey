import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class OptimizedDijkstra {
    public static List<Integer> shortestReach(int n, List<List<Integer>> edges, int s) {
        // Write your code here
        HashMap<Integer, Integer>[] graph = new HashMap[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new HashMap<>();

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);

            if (!graph[u].containsKey(v) || graph[u].get(v) > w) {
                graph[u].put(v, w);
                graph[v].put(u, w);
            }
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        // {cost,node}
        dist[s] = 0;
        pq.add(new int[] { 0, s });
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[1];
            int cost = curr[0];

            if (cost > dist[u])
                continue;

            for (Map.Entry<Integer, Integer> edge : graph[u].entrySet()) {
                int v = edge.getKey();
                int w = edge.getValue();
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new int[] { dist[v], v });
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i == s)
                continue;
            res.add(dist[i] == Integer.MAX_VALUE ? -1 : dist[i]);
        }
        return res;
    }
}
