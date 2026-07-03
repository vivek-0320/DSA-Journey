import java.util.*;

public class NetworkRecoverPathways {

    public boolean isPossible(int mid, List<List<int[]>> graph, boolean[] online, long k) {
        long[] dist = new long[online.length];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0])); // {totalCost,node}
        pq.add(new long[] { 0, 0 });
        dist[0] = 0;
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long totalCost = curr[0];
            int u = (int)curr[1];

            if (totalCost > dist[u])
                continue;

            if(u == online.length - 1)
                return totalCost <= k;

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];
                if (w < mid) 
                    continue;

                if (online[v] && totalCost + w <= dist[v]) {
                    dist[v] = totalCost + w;
                    pq.add(new long[] { dist[v], v });
                }
            }
        }
        return dist[online.length - 1] <= k;
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<List<int[]>> graph = new ArrayList<>(); // {node,cost}
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        int high = Integer.MIN_VALUE;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new int[] { v, w });
            high = Math.max(high, w);
        }

        int low = -1;
        high = high+1;

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (isPossible(mid, graph, online, k))
                low = mid;
            else
                high = mid;
        }

        return low;
    }
}