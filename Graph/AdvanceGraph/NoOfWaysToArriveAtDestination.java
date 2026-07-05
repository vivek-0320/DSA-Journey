import java.util.*;

class NoOfWaysToArriveAtDestination {

    // This approach uses only Dijkstra Algorithm to count ways of the shortest path.
    // The whole approach relies on : if we found a new path then(newPath < shortestPath) , ways[v] = ways[u]
    // else if (newPath == shortestPath) , ways[v] += ways[u]

    public int countPaths2(int n, int[][] roads) {
        final int MOD = 1_000_000_007;

        // Build adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int time = road[2];

            graph.get(u).add(new int[] { v, time });
            graph.get(v).add(new int[] { u, time });
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        int[] ways = new int[n];

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        dist[0] = 0;
        ways[0] = 1;
        // {current_time, node}
        pq.offer(new long[] { 0, 0 });

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();

            long currTime = curr[0];
            int node = (int) curr[1];

            // Ignore outdated entries
            if (currTime > dist[node]) {
                continue;
            }

            for (int[] edge : graph.get(node)) {
                int nbr = edge[0];
                int edgeTime = edge[1];

                // Found a shorter path
                if (currTime + edgeTime < dist[nbr]) {
                    dist[nbr] = currTime + edgeTime;
                    ways[nbr] = ways[node];
                    pq.offer(new long[] { dist[nbr], nbr });
                }

                // Found another shortest path
                else if (currTime + edgeTime == dist[nbr]) {
                    ways[nbr] = (ways[nbr] + ways[node]) % MOD;
                }
            }
        }

        return ways[n - 1];
    }

    // This Approach uses Dijkstra for finding shortest path, and Topo Sort + DP on
    // DAG for finding no ways of that path.
    // For Topo Sort + DP, the most essential rule is that , once a node has
    // completed its number of ways then
    // only it can transfer its details to other nodes.
    // For this we count inDegree of nodes and process them in increasing order
    // which makes order of process safe.
    // For each node u from queue, decrease the indegree of its neighbours node v
    // and dp[v] += dp[u].
    // When inDegree[v] == 0, push it into queue.

    public int countPaths(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        for (int[] edge : roads) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new int[] { v, w });
            graph.get(v).add(new int[] { u, w });
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        pq.add(new long[] { 0, 0 });
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long totalCost = curr[0];
            int u = (int) curr[1];

            if (totalCost > dist[u])
                continue;

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                int w = edge[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new long[] { dist[v], v });
                }
            }
        }
        System.out.println(Arrays.toString(dist));
        for (int i = 0; i < n; i++)
            graph.get(i).clear();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++)
            graph.get(i).clear();

        for (int[] edge : roads) {
            int u = edge[0], v = edge[1], w = edge[2];

            // Check direction u -> v
            if (dist[u] + w == dist[v]) {
                graph.get(u).add(new int[] { v, w });
                inDegree[v]++;
            }
            // Check direction v -> u
            else if (dist[v] + w == dist[u]) {
                graph.get(v).add(new int[] { u, w });
                inDegree[u]++;
            }
        }
        long[] dp = new long[n];
        dp[0] = 1;
        int MOD = 1_000_000_007;

        Deque<Integer> deq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                deq.add(i);
            }
        }
        while (!deq.isEmpty()) {
            int u = deq.poll();
            for (int[] edge : graph.get(u)) {
                int v = edge[0];

                dp[v] = (dp[v] + dp[u]) % MOD;

                inDegree[v]--;

                if (inDegree[v] == 0)
                    deq.add(v);
            }
        }
        return (int) dp[n - 1];
    }

}