import java.util.*;

public class MinTimeToReactWithLimitedPower {
    public long[] minTimeMaxPower(int n, int[][] edges, int power, int[] cost, int source, int target) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[] { edge[1], edge[2] });
        }

        // The maximum power we have ever had when popping a node.
        int[] maxPowerSeen = new int[n];
        Arrays.fill(maxPowerSeen, -1); // Fill with -1 since 0 power is a valid state

        // Priority Queue: {time, power, node}
        // Sort ascending by time. If tied, sort descending by power.
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return Long.compare(b[1], a[1]);
            return Long.compare(a[0], b[0]);
        });

        pq.add(new long[] { 0, power, source });

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long time = curr[0];
            int currPower = (int) curr[1];
            int u = (int) curr[2];

            // THE PARETO FILTER:
            // Because time only goes UP, this path is only useful if it brings strictly
            // MORE power than the best power we've seen so far.
            if (currPower <= maxPowerSeen[u]) {
                continue;
            }

            // Record this new high-score in power for node 'u'
            maxPowerSeen[u] = currPower;

            // Target check MUST happen after the Pareto filter, but before neighbors
            if (u == target) {
                return new long[] { time, currPower };
            }

            // Expand neighbors blindly! We will filter them when they pop out of the PQ.
            for (int[] nbr : graph.get(u)) {
                int v = nbr[0];
                int t = nbr[1];
                int powerLeft = currPower - cost[u];

                if (powerLeft >= 0) {
                    pq.add(new long[] { time + t, powerLeft, v });
                }
            }
        }

        return new long[] { -1, -1 };
    }
}
