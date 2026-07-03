import java.util.*;

public class CompanyRestructure {

    public static List<Integer> solve(int n, int[][] arr) {
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        int[] c = new int[n + 1];
        int root = -1;

        for (int i = 0; i < n; i++) {
            int nodeId = i + 1;
            int pi = arr[i][0];
            int ci = arr[i][1];

            c[nodeId] = ci;
            if (pi == -1) {
                root = nodeId;
            } else {
                tree.get(pi).add(nodeId);
            }
        }

        List<Integer> res = new ArrayList<>();

        // Start DFS exactly at the root, not its children
        if (root != -1) {
            dfs(root, res, tree, c);
        }

        Collections.sort(res);

        if (res.isEmpty()) {
            res.add(-1);
        }

        return res;
    }

    private static void dfs(int u, List<Integer> res, List<List<Integer>> tree, int[] c) {
        boolean canDelete = true;

        // Condition 1: The current node itself must have c = 1
        if (c[u] == 0) {
            canDelete = false;
        }

        // Condition 2: Check all children. If ANY child has c = 0, this node is saved.
        for (int child : tree.get(u)) {
            if (c[child] == 0) {
                canDelete = false;
            }

            // Recurse into the child regardless of whether the current node is deleted or
            // not
            dfs(child, res, tree, c);
        }

        // If both conditions are satisfied, mark this node for deletion
        if (canDelete) {
            res.add(u);
        }
    }

    private static List<Integer> solveOptimal(int n, int[][] arr) {
        boolean[] canDelete = new boolean[n + 1];
        Arrays.fill(canDelete, true);
        for (int i = 0; i < n; i++) {
            int nodeId = i + 1;
            int pi = arr[i][0];
            int ci = arr[i][1];

            if (ci == 0) {
                canDelete[nodeId] = false;
            }
            if (pi != -1 && ci == 0) {
                canDelete[pi] = false;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (canDelete[i])
                res.add(i);
        }
        if (res.isEmpty()) {
            res.add(-1);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        List<Integer> res = solveOptimal(n, arr);
        for (int r : res)
            System.out.print(r + " ");
        System.out.println();
    }
}
