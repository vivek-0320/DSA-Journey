
class PathExistenceI {

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

        int find(int node) {
            if (node == parent[node])
                return node;
            return parent[node] = find(parent[node]);
        }

        boolean conncted(int a, int b) {
            int pA = find(a);
            int pB = find(b);

            return pA == pB;
        }

        boolean union(int a, int b) {
            int pA = find(a);
            int pB = find(b);

            if (pA == pB)
                return false;

            if (size[pA] > size[pB]) {
                parent[pB] = pA;
                size[pA] += size[pA];
            } else {
                parent[pA] = pB;
                size[pB] += size[pA];
            }
            return true;
        }
    }

    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        DSU dsu = new DSU(n);
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && Math.abs(nums[i] - nums[j]) <= maxDiff) {
                dsu.union(i, j);
                j++;
            }
        }
        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            res[i] = dsu.conncted(a, b);
        }
        return res;
    }
}