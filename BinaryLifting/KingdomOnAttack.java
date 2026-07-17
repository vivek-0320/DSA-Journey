import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KingdomOnAttack {
    
    // Helper DFS to correctly build the parent array
    private static void dfs(int curr, int par, List<List<Integer>> tree, int[] parent) {
        parent[curr] = par;
        for (int neighbor : tree.get(curr)) {
            if (neighbor != par) { // Don't go backwards to the parent
                dfs(neighbor, curr, tree, parent);
            }
        }
    }

    public static int[] solve(int n, int[][] edges, int[] soldiers, int[][] queries) {
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            tree.add(new ArrayList<>());
            
        for (int i = 0; i < n - 1; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        // 2. Find true parents using DFS (Root is 1, parent of root is 0)
        int[] parent = new int[n + 1];
        dfs(1, 0, tree, parent);

        // 3. Initialize Binary Lifting Tables
        int maxLevel = 18;
        int[][] up = new int[n + 1][maxLevel];
        long[][] sum = new long[n + 1][maxLevel];
        
        for (int i = 1; i <= n; i++) {
            up[i][0] = parent[i];
            sum[i][0] = soldiers[i];
        }
        
        // 4. Populate the DP Tables
        for (int p = 1; p < maxLevel; p++) {
            for (int i = 1; i <= n; i++) {
                up[i][p] = up[up[i][p - 1]][p - 1];
                sum[i][p] = sum[i][p - 1] + sum[up[i][p - 1]][p - 1];
            }
        }

        // 5. Answer Queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            long b = queries[i][1]; // Use long for barbarians!

            for (int p = maxLevel - 1; p >= 0; p--) {
                // If jump stays in the tree AND barbarians can wipe out this chunk
                if (up[u][p] != 0 && b > sum[u][p]) {
                    b -= sum[u][p]; // Fight the citizens
                    u = up[u][p];   // Move forward
                }
            }
            // After the loop finishes, u is the exact city where they halt
            ans[i] = u;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        
        int[] soldiers = new int[n + 1];
        for (int i = 1; i <= n; i++)
            soldiers[i] = sc.nextInt();
            
        int[][] edges = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }
        
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }

        int[] result = solve(n, edges, soldiers, queries);
        for (int res : result) {
            System.out.println(res);
        }
    }
}