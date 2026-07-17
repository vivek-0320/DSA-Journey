import java.util.Arrays;

public class PathExistenceUsingBinaryLifting {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] edges = new int[n][2];
        for (int i = 0; i < n; i++) {
            edges[i][0] = nums[i];
            edges[i][1] = i;
        }
        Arrays.sort(edges, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[edges[i][1]] = i;
        }

        int MAX_JUMPS = 18;
        int[][] up = new int[n][MAX_JUMPS];

        // Two Pointers for O(N) precomputation
        int j = 0;
        for (int i = 0; i < n; i++) {
            j = Math.max(j, i); // j should never fall behind i
            int start = edges[i][0];
            while (j + 1 < n && start + maxDiff >= edges[j + 1][0]) {
                j++;
            }
            up[i][0] = Math.min(j, n - 1);
        }

        // Build the rest of the binary lifting table
        for (int p = 1; p < MAX_JUMPS; p++) {
            for (int i = 0; i < n; i++) {
                up[i][p] = up[up[i][p - 1]][p - 1];
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int src = pos[queries[i][0]];
            int dest = pos[queries[i][1]];

            if (src == dest) {
                answer[i] = 0;
                continue;
            }

            if (dest < src) {
                int t = src;
                src = dest;
                dest = t;
            }

            int steps = 0;
            int curr = src;

            for (int x = MAX_JUMPS - 1; x >= 0; x--) {
                int next = up[curr][x];
                if (next < dest) {
                    curr = next;
                    steps += (1 << x); 
                }
            }

            if (up[curr][0] >= dest) {
                steps += 1;
                answer[i] = steps;
            } else {
                answer[i] = -1;
            }
        }
        return answer;
    }
}
