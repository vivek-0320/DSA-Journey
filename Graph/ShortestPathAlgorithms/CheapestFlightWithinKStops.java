import java.util.Arrays;

public class CheapestFlightWithinKStops {
    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // Initialize the distances array with infinity
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            // Bellman-Ford: We relax the edges k + 1 times
            // k stops means we can use at most k + 1 edges
            
            for (int i = 0; i <= k; i++) {
                // Use a copy to prevent using updated distances from the same iteration
                int[] tmp = Arrays.copyOf(dist, n);

                for (int[] flight : flights) {
                    int u = flight[0];
                    int v = flight[1];
                    int price = flight[2];

                    // If the source of the edge has been reached
                    if (dist[u] != Integer.MAX_VALUE) {
                        if (dist[u] + price < tmp[v]) {
                            tmp[v] = dist[u] + price;
                        }
                    }
                }
                // Update the main distances array for the next iteration
                dist = tmp;
            }

            return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
        }
    }
}