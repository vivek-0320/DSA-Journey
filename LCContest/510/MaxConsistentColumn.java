public class MaxConsistentColumn {
    public int maxConsistentColumns(int[][] grid, int limit) {
        int cols = grid[0].length;
        int rows = grid.length;
        int[] dp = new int[cols];

        for (int i = 0; i < cols; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                boolean isValid = true;

                for (int k = 0; k < rows; k++) {
                    if (Math.abs(grid[k][i] - grid[k][j]) > limit) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[cols - 1];

    }

}
