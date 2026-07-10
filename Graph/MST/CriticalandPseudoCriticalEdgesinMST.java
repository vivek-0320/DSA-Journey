import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CriticalandPseudoCriticalEdgesinMST {
    class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        void reset() {
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int node) {
            if (node == parent[node])
                return node;
            return parent[node] = find(parent[node]); // Path compression
        }

        boolean connected(int a, int b) {
            return find(a) == find(b);
        }

        boolean union(int a, int b) {
            int pA = find(a);
            int pB = find(b);

            if (pA == pB)
                return false;

            if (size[pA] > size[pB]) {
                parent[pB] = pA;
                size[pA] += size[pB];
            } else {
                parent[pA] = pB;
                size[pB] += size[pA];
            }
            return true;
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int e = edges.length;

        // 1. Bundle the original indices before sorting
        int[][] edgs = new int[e][4];
        for (int i = 0; i < e; i++) {
            edgs[i][0] = edges[i][0]; // u
            edgs[i][1] = edges[i][1]; // v
            edgs[i][2] = edges[i][2]; // weight
            edgs[i][3] = i; // original index
        }

        Arrays.sort(edgs, (a, b) -> Integer.compare(a[2], b[2]));

        DSU dsu = new DSU(n);
        int mst = 0;
        for (int[] edge : edgs) {
            if (dsu.union(edge[0], edge[1])) {
                mst += edge[2];
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> critical = new ArrayList<>();
        HashSet<Integer> vis = new HashSet<>();

        // Find Critical Edges by excluding one edge at a time
        for (int i = 0; i < e; i++) {
            dsu.reset();
            int newMst = 0;
            int edgesUsed = 0;

            for (int j = 0; j < e; j++) {
                if (i == j)
                    continue;
                int u = edgs[j][0];
                int v = edgs[j][1];
                int w = edgs[j][2];

                if (dsu.union(u, v)) {
                    newMst += w;
                    edgesUsed++;
                }
            }

            // If the graph is disconnected OR the new MST is heavier, it's critical
            if (edgesUsed < n - 1 || newMst > mst) {
                critical.add(edgs[i][3]); // Add original index
                vis.add(i); 
            }
        }

        List<Integer> pseudo = new ArrayList<>();

        // Find Pseudo-Critical Edges by forcing one edge at a time
        for (int i = 0; i < e; i++) {
            if (vis.contains(i))
                continue; // Already known to be critical

            dsu.reset();
            int edgesUsed = 1; // We are forcefully using 1 edge right now

            int u = edgs[i][0];
            int v = edgs[i][1];
            int w = edgs[i][2];

            dsu.union(u, v);
            int newMst = w;

            for (int j = 0; j < e; j++) {
                if (i == j)
                    continue; // Already forced this edge

                u = edgs[j][0];
                v = edgs[j][1];
                w = edgs[j][2];

                if (dsu.union(u, v)) {
                    newMst += w;
                    edgesUsed++;
                }
            }

            // If it can still form a valid MST with the minimum weight, it's
            // pseudo-critical
            if (edgesUsed == n - 1 && newMst == mst) {
                pseudo.add(edgs[i][3]);
            }
        }

        res.add(critical);
        res.add(pseudo);
        return res;
    }
}
