public class MinScoreOfPath {

    // This problem states that the score of path is the cheapest edge between 1 to N. And an edge can be travlled 
    // any number of times. So this leaves us to find the cheapest edge connecting to node 1.
    // We use DSU to find connected components and check if a node is also connected by 1 and has cheapest edge cost.

    class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int findParent(int node) {
            if (node == parent[node])
                return node;
            return parent[node] = findParent(parent[node]);
        }

        public boolean union(int a, int b) {
            int pA = findParent(a);
            int pB = findParent(b);

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

    public int minScore(int n, int[][] roads) {
        DSU dsu = new DSU(n + 1);
        for (int[] road : roads) {
            dsu.union(road[0], road[1]);
        }
        int globalMin = Integer.MAX_VALUE;
        for (int[] road : roads) {
            if (dsu.findParent(road[0]) == dsu.findParent(1))
                globalMin = Math.min(globalMin, road[2]);
        }
        return globalMin;
    }
}
