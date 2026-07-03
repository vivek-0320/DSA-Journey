import java.util.*;

public class DijkstraStateSpace {
    public static long findRoute() {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return -1; 
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new int[] { v, w });
            graph.get(v).add(new int[] { u, w });
        }

        long[][] dist = new long[n + 1][2];
        for (int i = 0; i <= n; i++) {
            dist[i][0] = Long.MAX_VALUE;
            dist[i][1] = Long.MAX_VALUE;
        }
        
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );
        
        pq.add(new long[] { 0, 1, 1 });
        dist[1][1] = 0;
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long cost = curr[0];
            int u = (int) curr[1];
            int od = (int) curr[2];

            if (cost > dist[u][od]) continue;

            for (int[] edge : graph.get(u)) {
                int v = edge[0];
                long w = edge[1]; 
                
                if (dist[u][od] + w < dist[v][od]) {
                    dist[v][od] = dist[u][od] + w;
                    pq.add(new long[] { dist[v][od], v, od });
                }
            }

            if (od > 0) {
                for (int[] edge : graph.get(u)) {
                    int v = edge[0];
                    long w = edge[1] / 2; 
                    
                    if (dist[u][od] + w < dist[v][od - 1]) {
                        dist[v][od - 1] = dist[u][od] + w;
                        pq.add(new long[] { dist[v][od - 1], v, od - 1 });
                    }
                }
            }
        }
        sc.close();
        
        long ans = Math.min(dist[n][0], dist[n][1]);
        
        return ans == Long.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        System.out.println(findRoute());
    }
}