public class PizzaWth3nSlices {

    public int dfs(int idx, int k, int n, int[] slices, Integer[][] memo) {
        if (k == 0) {
            return 0;
        }

        if (idx > n)
            return Integer.MIN_VALUE / 2;

        if (memo[idx][k] != null)
            return memo[idx][k];

        // skip
        int skip = dfs(idx + 1, k, n, slices, memo);

        // take
        int take = slices[idx] + dfs(idx + 2, k - 1, n, slices, memo);

        memo[idx][k] = Math.max(skip, take);
        return memo[idx][k];
    }

    public int solveMemo(int[] slices) {
        int n = slices.length;
        int piecesLeft = n / 3;
        Integer[][] memoA = new Integer[n + 1][piecesLeft + 1];
        int first = dfs(0, piecesLeft, n - 2, slices, memoA);
        Integer[][] memoB = new Integer[n + 1][piecesLeft + 1];
        int second = dfs(1, piecesLeft, n - 1, slices, memoB);
        return Math.max(first, second);
    }

    public int solveTab(int[] slices) {
        int n = slices.length;
        int k = n / 3;

        // CASE 1: Allowed to pick from index 0 to n - 2
        int[][] dp1 = new int[n + 2][k + 1];

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 1; j <= k; j++) { // j is pieces we STILL need to pick
                // Choice 1: Skip the current slice
                int skip = dp1[i + 1][j];

                // Choice 2: Take the current slice. We gain its value, but need (j-1) more slices.
                int take = slices[i] + dp1[i + 2][j - 1];

                dp1[i][j] = Math.max(take, skip);
            }
        }
        // The answer for Case 1 is starting at index 0, needing to pick k slices
        int case1 = dp1[0][k];

        // CASE 2: Allowed to pick from index 1 to n - 1
        int[][] dp2 = new int[n + 2][k + 1];

        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= k; j++) {
                int skip = dp2[i + 1][j];
                int take = slices[i] + dp2[i + 2][j - 1];

                dp2[i][j] = Math.max(take, skip);
            }
        }
        // The answer for Case 2 is starting at index 1, needing to pick k slices
        int case2 = dp2[1][k];

        return Math.max(case1, case2);
    }

    public int maxSizeSlices(int[] slices) {
        return solveTab(slices);
    }
}
