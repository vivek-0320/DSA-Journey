import java.util.*;

public class TrafficLightsPath {

    public static int findPath(int n, int[] trafficCycle, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new int[] { v, w });
            graph.get(v).add(new int[] { u, w });
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // {totalTime,node}
        pq.add(new int[] { 0, 1 });
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int totalTime = curr[0];
            int u = curr[1];

            if (u == n)
                return totalTime;

            if (totalTime > dist[u])
                continue;

            int waitTime = 0;
            int cycle = totalTime / trafficCycle[u - 1];

            // If the cycle index is odd, it means the light is Red
            if (cycle % 2 != 0) {
                waitTime = trafficCycle[u - 1] - (totalTime % trafficCycle[u - 1]);
            }

            int departureTime = totalTime + waitTime;

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];

                if (departureTime + w < dist[v]) {
                    dist[v] = departureTime + w;
                    pq.add(new int[] { dist[v], v });
                }
            }
        }
        return dist[n] == Integer.MAX_VALUE ? -1 : dist[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] trafficCycle = new int[n];
        for (int i = 0; i < n; i++)
            trafficCycle[i] = sc.nextInt();
        int[][] edges = new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();
        }
        System.out.println(findPath(n, trafficCycle, edges));

    }
}
